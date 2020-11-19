package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.ConfigParameter;
import com.mall.cloud.model.mapper.system.ConfigParameterMapper;
import com.mall.cloud.passport.api.service.ParameterServerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>封装Qicloud项目ParameterServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 21:26
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class ParameterServerServiceImpl extends BaseServerService implements ParameterServerService {

    @Resource
    private ConfigParameterMapper configParameterMapper;

    /**
     * 条件查询列表数据
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param parameter 请求参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public DatagridResult list(Integer pageSize, Integer pageCount, ConfigParameter parameter) throws PassportServerException {
        DatagridResult result = new DatagridResult();
        PageHelper.startPage(pageCount,pageSize);
        //查询
        QueryWrapper<ConfigParameter> query = new QueryWrapper<>(parameter);
        query.lambda().ne(ConfigParameter::getStatus, 2);
        List<ConfigParameter> list = configParameterMapper.selectList(query);
        //返回列表
        result.setDataList(list);
        //PageHelper插件的
        PageInfo<ConfigParameter> pageInfo = new PageInfo<>(list);
        //返回总数
        result.setPageCount(pageInfo.getTotal());
        return result;
    }


    /**
     * 新增系统配置参数
     *
     * @param response  请求/响应
     * @param parameter 配置参数对象
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public ResponseResult save(ResponseResult response, ConfigParameter parameter) throws PassportServerException {
        QueryWrapper<ConfigParameter> query = new QueryWrapper<>();
        query.lambda().eq(ConfigParameter::getCode, parameter.getCode());
        int exist = configParameterMapper.selectCount(query);
        if (exist > 0) {
            response.setError("配置唯一码已存在，请重新设置!");
        }
        configParameterMapper.insert(parameter);
        return response;
    }

    /**
     * 编辑系统配置参数
     *
     * @param response  请求/响应
     * @param parameter 配置参数对象
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public ResponseResult edit(ResponseResult response, ConfigParameter parameter) throws PassportServerException {
        ConfigParameter parameters = configParameterMapper.selectById(parameter.getId());
        if (CheckEmptyUtil.isNotEmpty(parameters)) {
            QueryWrapper<ConfigParameter> query = new QueryWrapper<>();
            query.lambda().eq(ConfigParameter::getCode, parameter.getCode());
            query.lambda().ne(ConfigParameter::getId, parameter.getId());
            int exist = configParameterMapper.selectCount(query);
            if (exist > 0) {
                response.setError("配置唯一码已存在，请重新设置!");
                return response;
            }
            configParameterMapper.updateById(parameter);
            return response;
        } else {
            throw new PassportServerException("未找到该系统配置");
        }
    }

    /**
     * 根据id获取系统配置的详情
     *
     * @param id 系统配置参数id
     * @return 返回详情
     */
    @Override
    public ConfigParameter getDetailById(Long id) {
        return configParameterMapper.selectById(id);
    }
}
