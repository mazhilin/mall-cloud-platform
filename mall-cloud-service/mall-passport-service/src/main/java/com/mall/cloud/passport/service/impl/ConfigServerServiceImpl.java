package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.ConfigParameter;
import com.mall.cloud.model.entity.system.MenuInfo;
import com.mall.cloud.model.mapper.system.ConfigParameterMapper;
import com.mall.cloud.passport.api.param.RequestConfigParam;
import com.mall.cloud.passport.api.service.ConfigServerService;
import com.mall.cloud.passport.api.service.ParameterServerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>封装Qicloud项目ConfigServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-21 22:25
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class ConfigServerServiceImpl extends BaseServerService implements ConfigServerService {
    @Resource
    private ConfigParameterMapper configParameterMapper;

    /**
     * 条件查询列表数据
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param param     请求参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public DatagridResult list(Integer pageSize, Integer pageLimit, RequestConfigParam param) throws PassportServerException {
        DatagridResult result = new DatagridResult();
        PageHelper.startPage(pageSize, pageLimit);
        //查询列表
        QueryWrapper<ConfigParameter> queryMenu = new QueryWrapper<>();
        if (CheckEmptyUtil.isNotEmpty(param.getName())) {
            queryMenu.lambda().eq(ConfigParameter::getName, param.getName());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getStatus())) {
            queryMenu.lambda().eq(ConfigParameter::getStatus, param.getStatus());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getScope())) {
            queryMenu.lambda().eq(ConfigParameter::getScope, param.getScope());
        }
        queryMenu.lambda().eq(ConfigParameter::getIsDelete, Constants.NO);
        queryMenu.lambda().orderByDesc(ConfigParameter::getCreateTime);
        List<ConfigParameter> queryList = configParameterMapper.selectList(queryMenu);
        result.setDataList(queryList);
        PageInfo<ConfigParameter> pageInfo = new PageInfo<>(queryList);
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
        return null;
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
        return null;
    }

    /**
     * 根据id获取系统配置的详情
     *
     * @param id 系统配置参数id
     * @return 返回详情
     */
    @Override
    public ConfigParameter getDetailById(Long id) {
        return null;
    }
}
