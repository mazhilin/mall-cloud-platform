package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.model.entity.system.MenuInfo;

import java.util.List;

/**
 * <p>封装Qicloud项目MenuServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 23:18
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface MenuServerService extends BaseService {

    /**
     * 分页查询菜单列表
     *
     * @param pageCount 页码数
     * @param pageSize  条目数
     * @param menu      参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    DatagridResult list(Integer pageCount, Integer pageSize, MenuInfo menu) throws PassportServerException;

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
    MenuInfo getDetailById(Long id);

    /**
     * 获取权限菜单的树结构列表（逻辑删除排出）
     *
     * @param parentId 父菜单id
     * @param scope    权限作用域
     * @param status   状态
     * @return 返回权限菜单树列表
     */
    List<MenuInfo> authMenuTreeList(Long parentId, Integer scope, Integer status);

    /**
     * 获取全部菜单的树结构列表（逻辑删除排出）
     *
     * @param id 菜单id
     * @return 菜单树结构列表
     */
    List<MenuInfo> menuTreeList(Long id);
}
