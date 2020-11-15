package com.mall.cloud.console.service.impl;

import cn.hutool.core.collection.CollUtil;
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
            QueryWrapper<MenuInfo> queryMenu = new QueryWrapper<>();
            queryMenu.lambda().eq(MenuInfo::getCode, "root");
            queryMenu.lambda().eq(MenuInfo::getIsInnerMenu, Constants.YES);
            menuInfo = menuInfoMapper.selectOne(queryMenu);
            if (CheckEmptyUtil.isNotEmpty(menuInfo)){
                parentId = menuInfo.getParentId();
            }
        }
        // 判断用户类型是否为管理员
        List<MenuInfo> rootMenuList = Lists.newLinkedList();
        List<MenuInfo> childMenuList = Lists.newLinkedList();
        AdminUser adminUser = adminUserMapper.selectById(userId);
        if (Objects.equals(UserType.ADMIN.code(), adminUser.getType())) {
            // 获取根目录下的一级菜单
            QueryWrapper<MenuInfo> queryRoots = new QueryWrapper<>();
            queryRoots.lambda().eq(MenuInfo::getParentId, parentId);
            queryRoots.lambda().eq(MenuInfo::getMenuAuth, MenuAuthType.ADMIN.code());
            queryRoots.lambda().eq(MenuInfo::getStatus, Constants.ENABLE);
            queryRoots.lambda().eq(MenuInfo::getIsDelete, Constants.NO);
            queryRoots.lambda().orderByAsc(MenuInfo::getSort);
            rootMenuList = menuInfoMapper.selectList(queryRoots);
            //若不存在父级ID，则先查出根目录下的第一级菜单列表，再查询第一个菜单的子级菜单列表
            if (CollUtil.isNotEmpty(rootMenuList)) {
                parentId = rootMenuList.get(0).getId();
            }
            QueryWrapper<MenuInfo> queryhilds = new QueryWrapper<>();
            queryRoots.lambda().eq(MenuInfo::getParentId, parentId);
            queryRoots.lambda().eq(MenuInfo::getMenuAuth, MenuAuthType.ADMIN.code());
            queryRoots.lambda().eq(MenuInfo::getStatus, Constants.ENABLE);
            queryRoots.lambda().eq(MenuInfo::getIsDelete, Constants.NO);
            queryRoots.lambda().orderByAsc(MenuInfo::getSort);
            childMenuList = menuInfoMapper.selectList(queryhilds);
        }
        result.putResult("rootMenuList", rootMenuList);
        result.putResult("childMenuList", childMenuList);
        return result;
    }
}
