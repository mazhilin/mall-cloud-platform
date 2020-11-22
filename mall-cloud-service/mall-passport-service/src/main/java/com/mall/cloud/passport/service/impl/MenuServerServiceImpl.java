package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.MenuAuthType;
import com.mall.cloud.common.constant.UserType;
import com.mall.cloud.common.exception.ConsoleServerException;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.MenuInfo;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.mapper.system.MenuInfoMapper;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.passport.api.param.RequestMenuParam;
import com.mall.cloud.passport.api.service.MenuServerService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>封装Qicloud项目MenuServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 23:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class MenuServerServiceImpl extends BaseServerService implements MenuServerService {

    @Resource
    private MenuInfoMapper menuInfoMapper;
    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 分页查询菜单列表
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param param     参数对象
     * @return 返回结果
     * @throws ConsoleServerException 异常消息
     */
    @Override
    public DatagridResult list(Integer pageSize, Integer pageLimit, RequestMenuParam param) throws ConsoleServerException {
        DatagridResult result = new DatagridResult();
        PageHelper.startPage(pageSize, pageLimit);
        //查询列表
        QueryWrapper<MenuInfo> queryMenu = new QueryWrapper<>();
        if (CheckEmptyUtil.isNotEmpty(param.getParentId())) {
            queryMenu.lambda().eq(MenuInfo::getParentId, param.getParentId());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getName())) {
            queryMenu.lambda().eq(MenuInfo::getName, param.getName());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getStatus())) {
            queryMenu.lambda().eq(MenuInfo::getStatus, param.getStatus());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getScope())) {
            queryMenu.lambda().eq(MenuInfo::getScope, param.getScope());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getMenuAuth())) {
            queryMenu.lambda().eq(MenuInfo::getMenuAuth, param.getMenuAuth());
        }
        queryMenu.lambda().eq(MenuInfo::getIsDelete, Constants.NO);
        queryMenu.lambda().orderByDesc(MenuInfo::getCreateTime);
        List<MenuInfo> queryList = menuInfoMapper.selectList(queryMenu);
        result.setDataList(queryList);
        PageInfo<MenuInfo> pageInfo = new PageInfo<>(queryList);
        result.setPageCount(pageInfo.getTotal());
        return result;
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public int save(MenuInfo menu) throws PassportServerException {
        QueryWrapper<MenuInfo> query = new QueryWrapper<>();
        query.lambda().ne(MenuInfo::getParentId, menu.getParentId());
        query.lambda().select(MenuInfo::getCode);
        query.lambda().orderByDesc(MenuInfo::getSort);
        query.lambda().last("limit 1");
        MenuInfo preMenu = menuInfoMapper.selectOne(query);
        if (CheckEmptyUtil.isEmpty(preMenu)) {
            MenuInfo parentMenu = menuInfoMapper.selectById(menu.getParentId());
            menu.setCode(parentMenu.getCode() + "001");
        } else {
            long preCode = Long.parseLong(preMenu.getCode());
            String nextCode = String.valueOf(preCode + 1L);
            menu.setCode(nextCode);
        }
        return menuInfoMapper.insert(menu);
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public int edit(MenuInfo menu) throws PassportServerException {
        return menuInfoMapper.updateById(menu);
    }

    /**
     * 根据ID查询菜单详情
     *
     * @param id 菜单ID
     * @return 菜单详情
     */
    @Override
    public MenuInfo getDetailById(Long id) {
        return null;
    }

    /**
     * 获取权限菜单的树结构列表（逻辑删除排出）
     *
     * @param parentId 父菜单id
     * @param scope    权限作用域
     * @param status   状态
     * @return 返回权限菜单树列表
     */
    @Override
    public List<MenuInfo> authMenuTreeList(Long parentId, Integer scope, Integer status) {
        return null;
    }

    /**
     * 获取全部菜单的树结构列表（逻辑删除排出）
     *
     * @param id 菜单id
     * @return 菜单树结构列表
     */
    @Override
    public List<MenuInfo> menuTreeList(Long id) {
        return null;
    }

    /**
     * 系统后台-菜单管理-选项卡
     *
     * @param userId 菜单id
     * @return 菜单树结构列表
     * @throws ConsoleServerException 异常消息
     */
    @Override
    public List<MenuInfo> category(String userId) throws ConsoleServerException {
// 判断父级菜单ID是否为空
        QueryWrapper<MenuInfo> queryRootMenu = new QueryWrapper<>();
        queryRootMenu.lambda().eq(MenuInfo::getCode, "root");
        queryRootMenu.lambda().eq(MenuInfo::getIsInnerMenu, Constants.YES);
        queryRootMenu.lambda().select(MenuInfo::getId);
        MenuInfo menuInfo = menuInfoMapper.selectOne(queryRootMenu);
        AdminUser adminUser = adminUserMapper.selectById(userId);
        Integer menuAuthType;
        UserType userType = UserType.get(adminUser.getType());
        List<MenuInfo> menuList = Lists.newLinkedList();
        switch (Objects.requireNonNull(userType)) {
            case ADMIN:
                menuList =this.queryAllMenuList(menuInfo.getId());
                break;
            case COMPANY:
                menuAuthType = MenuAuthType.COMPANY.code();
                menuList =this.queryAuthMenuList(menuInfo.getId(), menuAuthType);
                break;
            case EMPLOYEE:
                menuAuthType = MenuAuthType.EMPLOYEE.code();
                menuList =this.queryAuthMenuList(menuInfo.getId(), menuAuthType);
                break;
            default:
                menuList =this.queryAllMenuList(menuInfo.getId());
                break;
        }
        return menuList;
    }


    /**
     * 获取一级菜单目录
     *
     * @param parentId     父级菜单id
     * @param menuAuthType 菜单授权类型
     * @return 返回一级菜单列表
     */
    private List<MenuInfo> queryAuthMenuList(String parentId, Integer menuAuthType) {
        // 获取根目录下的一级菜单
        QueryWrapper<MenuInfo> queryParentMenu = new QueryWrapper<>();
        queryParentMenu.lambda().eq(MenuInfo::getParentId, parentId);
        queryParentMenu.lambda().eq(MenuInfo::getMenuAuth, menuAuthType);
        queryParentMenu.lambda().eq(MenuInfo::getStatus, Constants.ENABLE);
        queryParentMenu.lambda().eq(MenuInfo::getIsDelete, Constants.NO);
        queryParentMenu.lambda().orderByAsc(MenuInfo::getSort);
        return menuInfoMapper.selectList(queryParentMenu);
    }

    /**
     * 获取一级菜单目录
     *
     * @param parentId     父级菜单id
     * @return 返回一级菜单列表
     */
    private List<MenuInfo> queryAllMenuList(String parentId) {
        // 获取根目录下的一级菜单
        QueryWrapper<MenuInfo> queryParentMenu = new QueryWrapper<>();
        queryParentMenu.lambda().eq(MenuInfo::getParentId, parentId);
        queryParentMenu.lambda().ne(MenuInfo::getCode, "root");
        queryParentMenu.lambda().eq(MenuInfo::getType, Constants.NO);
        queryParentMenu.lambda().eq(MenuInfo::getStatus, Constants.ENABLE);
        queryParentMenu.lambda().eq(MenuInfo::getIsDelete, Constants.NO);
        queryParentMenu.lambda().orderByAsc(MenuInfo::getSort);
        return menuInfoMapper.selectList(queryParentMenu);
    }
}
