package com.mall.cloud.console.api.service;

import com.mall.cloud.common.exception.ConsoleServerException;
import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.common.restful.ResponseResult;

/**
 * <p>封装Qicloud项目AuthorityServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-13 20:12
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface AuthorityServerService extends BaseService {
    /**
     * 取消用户-角色关系
     *
     * @param userId  用户ID
     * @param roleIds 多个角色ID(以“,”分割)
     */
    void cancelUserRole(String userId, String roleIds);

    /**
     * 绑定用户-角色关系
     *
     * @param userId  用户ID
     * @param roleIds 多个角色ID(以“,”分割)
     */
    void bindUserRole(String userId, String roleIds);

    /**
     * 取消/绑定用户-角色关系[通用接口]
     *
     * @param userId  用户ID
     * @param roleIds 多个角色ID(以“,”分割)
     */
    void bindOrCancelUserRole(String userId, String roleIds);

    /**
     * 绑定角色-菜单关系
     *
     * @param roleId  角色ID
     * @param menuIds 多个菜单ID(以“,”分割)
     */
    void bindRoleMenu(String roleId, String menuIds);

    /**
     * 绑定角色-菜单关系
     *
     * @param roleId  角色ID
     * @param menuIds 多个菜单ID(以“,”分割)
     */
    void cancelRoleMenu(String roleId, String menuIds);

    /**
     * 绑定/取消角色-菜单关系[通用接口]
     *
     * @param roleId  角色ID
     * @param menuIds 多个菜单ID(以“,”分割)
     */
    void bindOrCancelRoleMenu(String roleId, String menuIds);

    /**
     * 查询用户绑定的菜单树形结构列表
     * @param result 结果
     * @param userId 用户id
     * @param parentId 父级菜单ID
     * @return 结果
     * @throws ConsoleServerException 异常
     */
    ResponseResult showMenuList(ResponseResult result, String userId, String parentId) throws ConsoleServerException;

}
