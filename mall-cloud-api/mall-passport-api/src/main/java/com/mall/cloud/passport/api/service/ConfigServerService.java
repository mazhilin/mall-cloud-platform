package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.model.entity.system.ConfigParameter;
import com.mall.cloud.passport.api.param.RequestConfigParam;

/**
 * <p>封装Qicloud项目ConfigServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-21 22:23
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface ConfigServerService extends BaseService {

    /**
     * 条件查询列表数据
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param param 请求参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    DatagridResult list(Integer pageSize, Integer pageLimit, RequestConfigParam param) throws PassportServerException;
    /**
     * 新增系统配置参数
     *
     * @param response  请求/响应
     * @param parameter 配置参数对象
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    ResponseResult save(ResponseResult response, ConfigParameter parameter) throws PassportServerException;

    /**
     * 编辑系统配置参数
     *
     * @param response  请求/响应
     * @param parameter 配置参数对象
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    ResponseResult edit(ResponseResult response, ConfigParameter parameter) throws PassportServerException;

    /**
     * 根据id获取系统配置的详情
     *
     * @param id 系统配置参数id
     * @return 返回详情
     */
    ConfigParameter getDetailById(Long id);
}
