package com.mall.cloud.passport.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.Resources;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.SequenceServerUtil;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.passport.api.param.RequestUserParam;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.UserServerService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

/**
 * <p>封装Qicloud项目UserServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 19:55
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserServerServiceImpl extends BaseServerService implements UserServerService {
    @DubboConsumerClient
    private RedisOperationsService<String, Object> redisOperationsService;
    @DubboConsumerClient
    private ValueOperationsService<String, Object> valueOperationsService;
    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 验证并刷新token
     *
     * @param token token
     * @return 用户数据
     */
    @Override
    public AdminUser validationAndRefreshToken(String token) {
        // 判断Token是否为空
        if (CheckEmptyUtil.isEmpty(token)) {
            return null;
        }
        AdminUser adminUser = null;
        // 验证Token生命周期
        String tokenKey = Resources.APP_USER_LOGIN_TOKEN + token;
        try {
            //验证reids中有没有
            Object userData = valueOperationsService.get(tokenKey);
            if (ObjectUtil.isNotNull(userData) && StrUtil.isNotBlank(userData.toString())) {
                //重新设置 30 分钟
                int expireSeconds = 60 * 30;
                redisOperationsService.expire(tokenKey, Duration.ofDays(expireSeconds));
                //刷新成功返回用户数据
                JSONObject userJson = JSONObject.parseObject(userData.toString());
                adminUser = JSONObject.toJavaObject(userJson, AdminUser.class);
            }
        } catch (Exception e) {
            logger.error("后台token验证时，更新redis token值失败：{}", e);
            return null;
        }
        return adminUser;
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @Override
    public AdminUser queryUserInfo(String userId) {
        return adminUserMapper.selectById(userId);
    }

    /**
     * 系统中心-用户管理-分页查询列表
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param param     用户请求参数
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public DatagridResult list(Integer pageSize, Integer pageLimit, RequestUserParam param) throws PassportServerException {
        DatagridResult result = new DatagridResult();
        PageHelper.startPage(pageSize, pageLimit);
        //查询列表
        QueryWrapper<AdminUser> queryUser = new QueryWrapper<>();
        if (CheckEmptyUtil.isNotEmpty(param.getAccount())) {
            queryUser.lambda().eq(AdminUser::getAccount, param.getAccount());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getName())) {
            queryUser.lambda().eq(AdminUser::getName, param.getName());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getMobile())) {
            queryUser.lambda().eq(AdminUser::getMobile, param.getMobile());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getStatus())) {
            queryUser.lambda().eq(AdminUser::getStatus, param.getStatus());
        }
        queryUser.lambda().eq(AdminUser::getIsDelete, Constants.NO);
        queryUser.lambda().orderByDesc(AdminUser::getCreateTime);
        List<AdminUser> queryList = adminUserMapper.selectList(queryUser);
        result.setDataList(queryList);
        PageInfo<AdminUser> pageInfo = new PageInfo<>(queryList);
        result.setPageCount(pageInfo.getTotal());
        return result;
    }

    /**
     * 系统中心-用户管理-查询用户详情
     *
     * @param id 用户id
     * @return 返回结果
     */
    @Override
    public AdminUser show(String id) {
        return null;
    }

    /**
     * 系统中心-用户管理-查询用户详情
     *
     * @param id 用户id
     * @return 返回结果
     */
    @Override
    public AdminUser detail(String id) {
        return adminUserMapper.selectById(id);
    }

    /**
     * 系统中心-用户管理-新增
     *
     * @param param 用户请求参数
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public Integer save(RequestUserParam param) throws PassportServerException {
        AdminUser admin = new AdminUser();
        BeanUtils.copyProperties(param, admin);
        admin.setId(String.valueOf(SequenceServerUtil.getInstance().produceId()));
        return adminUserMapper.insert(admin);
    }

    /**
     * 系统中心-用户管理-编辑
     *
     * @param result 请求/响应结果
     * @param param  用户请求参数
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public ResponseResult edit(ResponseResult result, RequestUserParam param) throws PassportServerException {
        return null;
    }

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
    @Override
    public ResponseResult update(ResponseResult result, String id, Integer status, String operatorId) throws PassportServerException {
        return null;
    }

}
