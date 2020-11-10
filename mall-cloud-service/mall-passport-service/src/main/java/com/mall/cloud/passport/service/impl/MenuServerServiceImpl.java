package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.MenuInfo;
import com.mall.cloud.model.mapper.system.MenuInfoMapper;
import com.mall.cloud.passport.api.service.MenuServerService;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 分页查询菜单列表
     *
     * @param pageCount 页码数
     * @param pageSize  条目数
     * @param menu      参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public DatagridResult list(Integer pageCount, Integer pageSize, MenuInfo menu) throws PassportServerException {
        return null;
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
}
