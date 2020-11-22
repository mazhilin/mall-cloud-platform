package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.exception.ConsoleServerException;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.model.entity.system.MenuInfo;
import com.mall.cloud.passport.api.param.RequestMenuParam;
import com.mall.cloud.passport.api.param.RequestRoleParam;

/**
 * <p>封装Qicloud项目RoleServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-22 15:59
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface RoleServerService extends BaseService {
    /**
     * 分页查询菜单列表
     *
     * @param pageSize 页码数
     * @param pageLimit  条目数
     * @param param      参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    DatagridResult list(Integer pageSize, Integer pageLimit, RequestRoleParam param) throws PassportServerException;

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    int save(MenuInfo menu) throws PassportServerException;

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    int edit(MenuInfo menu) throws PassportServerException;

    /**
     * 根据ID查询菜单详情
     *
     * @param id 菜单ID
     * @return 菜单详情
     */
    MenuInfo detail(Long id);
}
