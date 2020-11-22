package com.mall.cloud.console.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.MenuAuthType;
import com.mall.cloud.common.constant.UserType;
import com.mall.cloud.common.exception.ConsoleServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.console.api.service.AuthorityServerService;
import com.mall.cloud.model.entity.system.MenuInfo;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.mapper.system.MenuInfoMapper;
import com.mall.cloud.model.mapper.user.AdminUserMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>封装Qicloud项目AuthorityServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-13 20:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class AuthorityServerServiceImpl extends BaseServerService implements AuthorityServerService {
    @Resource
    private MenuInfoMapper menuInfoMapper;
    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 取消用户-角色关系
     *
     * @param userId  用户ID
     * @param roleIds 多个角色ID(以“,”分割)
     */
    @Override
    public void cancelUserRole(String userId, String roleIds) {

    }

    /**
     * 绑定用户-角色关系
     *
     * @param userId  用户ID
     * @param roleIds 多个角色ID(以“,”分割)
     */
    @Override
    public void bindUserRole(String userId, String roleIds) {

    }

    /**
     * 取消/绑定用户-角色关系[通用接口]
     *
     * @param userId  用户ID
     * @param roleIds 多个角色ID(以“,”分割)
     */
    @Override
    public void bindOrCancelUserRole(String userId, String roleIds) {

    }

    /**
     * 绑定角色-菜单关系
     *
     * @param roleId  角色ID
     * @param menuIds 多个菜单ID(以“,”分割)
     */
    @Override
    public void bindRoleMenu(String roleId, String menuIds) {

    }

    /**
     * 绑定角色-菜单关系
     *
     * @param roleId  角色ID
     * @param menuIds 多个菜单ID(以“,”分割)
     */
    @Override
    public void cancelRoleMenu(String roleId, String menuIds) {

    }

    /**
     * 绑定/取消角色-菜单关系[通用接口]
     *
     * @param roleId  角色ID
     * @param menuIds 多个菜单ID(以“,”分割)
     */
    @Override
    public void bindOrCancelRoleMenu(String roleId, String menuIds) {

    }

    /**
     * 查询用户绑定的菜单树形结构列表
     *
     * @param result   结果
     * @param userId   用户id
     * @param parentId 父级菜单ID
     * @return 结果
     * @throws ConsoleServerException 异常
     */
    @Override
    public ResponseResult showMenuList(ResponseResult result, String userId, String parentId) throws ConsoleServerException {
        // 判断父级菜单ID是否为空
        MenuInfo menuInfo = null;
        if (CheckEmptyUtil.isEmpty(parentId)) {
            QueryWrapper<MenuInfo> queryRootMenu = new QueryWrapper<>();
            queryRootMenu.lambda().eq(MenuInfo::getCode, "root");
            queryRootMenu.lambda().eq(MenuInfo::getIsInnerMenu, Constants.YES);
            queryRootMenu.lambda().select(MenuInfo::getId);
            menuInfo = menuInfoMapper.selectOne(queryRootMenu);
            if (CheckEmptyUtil.isNotEmpty(menuInfo)) {
                parentId = menuInfo.getId();
            }
        }
        AdminUser adminUser = adminUserMapper.selectById(userId);
        Integer menuAuthType;
        UserType userType = UserType.get(adminUser.getType());
        switch (Objects.requireNonNull(userType)) {
            case ADMIN:
                menuAuthType = MenuAuthType.ADMIN.code();
                break;
            case COMPANY:
                menuAuthType = MenuAuthType.COMPANY.code();
                break;
            case EMPLOYEE:
                menuAuthType = MenuAuthType.EMPLOYEE.code();
                break;
            default:
                menuAuthType = MenuAuthType.DEFAULT.code();
                break;
        }
        // 查询一级菜单列表
        List<MenuInfo> parentMenuList = this.queryRootMenuList(parentId, menuAuthType);
        List<MenuInfo> menuTreeList = this.sortMenuList(parentMenuList, menuAuthType);
        result.putResult("menuList", CheckEmptyUtil.isNotEmpty(menuTreeList) ? menuTreeList : Lists.newLinkedList());
        return result;
    }

    /**
     * 获取一级菜单目录
     *
     * @param parentId     父级菜单id
     * @param menuAuthType 菜单授权类型
     * @return 返回一级菜单列表
     */
    private List<MenuInfo> queryRootMenuList(String parentId, Integer menuAuthType) {
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
     * 处理对应菜单数据列表
     *
     * @param parentMenuList 父级菜单列表
     * @param menuAuthType   菜单授权类型
     * @return 返回列表
     */
    private List<MenuInfo> sortMenuList(List<MenuInfo> parentMenuList, Integer menuAuthType) {
        List<MenuInfo> menuList = Lists.newLinkedList();
        //
        if (CheckEmptyUtil.isNotEmpty(parentMenuList)) {
            for (int index = 0, item = parentMenuList.size(); index < item; index++) {
                menuList.add(parentMenuList.get(index));
            }
            parentMenuList.parallelStream().forEachOrdered(item -> {
                item.setChildMenuList(queryChildMenuList(item.getId(), menuAuthType));
            });
        }
        return menuList;
    }

    /**
     * 获取二级菜单目录
     *
     * @param parentId     父级菜单id
     * @param menuAuthType 菜单授权类型
     * @return 返回一级菜单列表
     */
    private List<MenuInfo> queryChildMenuList(String parentId, Integer menuAuthType) {
        // 获取根目录下的二级菜单
        QueryWrapper<MenuInfo> queryChildMenu = new QueryWrapper<>();
        queryChildMenu.lambda().eq(MenuInfo::getParentId, parentId);
        queryChildMenu.lambda().eq(MenuInfo::getMenuAuth, menuAuthType);
        queryChildMenu.lambda().eq(MenuInfo::getStatus, Constants.ENABLE);
        queryChildMenu.lambda().eq(MenuInfo::getIsDelete, Constants.NO);
        queryChildMenu.lambda().orderByAsc(MenuInfo::getSort);
        List<MenuInfo> queryResultList = menuInfoMapper.selectList(queryChildMenu);
        List<MenuInfo> childMenuList = Lists.newLinkedList();
        if (CheckEmptyUtil.isNotEmpty(queryResultList)) {
            queryResultList.parallelStream().forEachOrdered(item -> {
                if (Objects.equals(item.getParentId(), parentId)) {
                    childMenuList.add(item);
                }
            });
        }
        Objects.requireNonNull(childMenuList).parallelStream().forEachOrdered(item -> {
            if (CheckEmptyUtil.isEmpty(item.getEvent())) {
                item.setChildMenuList(queryChildMenuList(item.getId(), menuAuthType));
            }
        });
        if (childMenuList.size() == 0) {
            return null;
        }
        return childMenuList;
    }

}
