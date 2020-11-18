package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.param.RequestUserParam;

/**
 * <p>封装Qicloud项目AdminUserService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-24 00:52
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface UserServerService extends BaseService {

    /**
     * 验证并刷新token
     *
     * @param token token
     * @return 用户数据
     */
    AdminUser validationAndRefreshToken(String token);

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return 返回结果
     */
    AdminUser queryUserInfo(String userId);


    /**
     * 系统中心-用户管理-分页查询列表
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param param     用户请求参数
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    DatagridResult list(Integer pageSize, Integer pageLimit, RequestUserParam param) throws PassportServerException;

    /**
     * 系统中心-用户管理-查询用户详情
     *
     * @param id 用户id
     * @return 返回结果
     */
    AdminUser show(String id);


    /**
     * 系统中心-用户管理-查询用户详情
     *
     * @param id 用户id
     * @return 返回结果
     */
    AdminUser detail(String id);

    /**
     * 系统中心-用户管理-新增
     *
     * @param param 用户请求参数
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    Integer save(RequestUserParam param) throws PassportServerException;

    /**
     * 系统中心-用户管理-编辑
     *
     * @param result 请求/响应结果
     * @param param  用户请求参数
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    ResponseResult edit(ResponseResult result, RequestUserParam param) throws PassportServerException;


    /**
     * 系统中心-用户管理-修改用户状态
     *
     * @param result     请求/响应结果
     * @param id         用戶id
     * @param status     请求参数状态
     * @param operatorId 操作人id
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    ResponseResult update(ResponseResult result, String id, Integer status, String operatorId) throws PassportServerException;
}
