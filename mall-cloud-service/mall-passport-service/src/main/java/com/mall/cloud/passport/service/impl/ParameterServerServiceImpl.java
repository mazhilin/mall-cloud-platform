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
import com.mall.cloud.model.entity.system.PublicParameter;
import com.mall.cloud.model.mapper.system.ConfigParameterMapper;
import com.mall.cloud.model.mapper.system.PublicParameterMapper;
import com.mall.cloud.passport.api.param.RequestPublicParam;
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
    private PublicParameterMapper publicParameterMapper;

    /**
     * 条件查询列表数据
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param param 请求参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public DatagridResult list(Integer pageSize, Integer pageCount, RequestPublicParam param) throws PassportServerException {
        DatagridResult result = new DatagridResult();
        PageHelper.startPage(pageCount,pageSize);
        //查询
        QueryWrapper<PublicParameter> queryParam = new QueryWrapper<>();
        queryParam.lambda().ne(PublicParameter::getStatus, 2);
        List<PublicParameter> list = publicParameterMapper.selectList(queryParam);
        //返回列表
        result.setDataList(list);
        //PageHelper插件的
        PageInfo<PublicParameter> pageInfo = new PageInfo<>(list);
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
    public ResponseResult save(ResponseResult response, PublicParameter parameter) throws PassportServerException {
        QueryWrapper<PublicParameter> query = new QueryWrapper<>();
        query.lambda().eq(PublicParameter::getCode, parameter.getCode());
        int exist = publicParameterMapper.selectCount(query);
        if (exist > 0) {
            response.setError("配置唯一码已存在，请重新设置!");
        }
        publicParameterMapper.insert(parameter);
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
    public ResponseResult edit(ResponseResult response, PublicParameter parameter) throws PassportServerException {
        PublicParameter parameters = publicParameterMapper.selectById(parameter.getId());
        if (CheckEmptyUtil.isNotEmpty(parameters)) {
            QueryWrapper<PublicParameter> query = new QueryWrapper<>();
            query.lambda().eq(PublicParameter::getCode, parameter.getCode());
            query.lambda().ne(PublicParameter::getId, parameter.getId());
            int exist = publicParameterMapper.selectCount(query);
            if (exist > 0) {
                response.setError("配置唯一码已存在，请重新设置!");
                return response;
            }
            publicParameterMapper.updateById(parameter);
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
    public PublicParameter getDetailById(Long id) {
        return publicParameterMapper.selectById(id);
    }
}
