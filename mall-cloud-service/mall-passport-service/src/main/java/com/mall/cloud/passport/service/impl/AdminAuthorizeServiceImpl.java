package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.Resources;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.ConfigParameter;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.mapper.system.ConfigParameterMapper;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.passport.api.service.*;
import com.mall.cloud.passport.service.authorize.GeneralApplicationAuthorize;
import org.springframework.dao.DataAccessException;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 封装Qicloud项目AdminAuthorizeServiceImpl类.<br>
 *
 * <p>//TODO...<br>
 *
 * @author Powered by marklin 2020-11-12 20:53
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.
 * <br>
 */
@DubboProviderServer
public class AdminAuthorizeServiceImpl extends GeneralApplicationAuthorize
        implements AdminAuthorizeService {
    @DubboConsumerClient
    protected ValueOperationsService<String, String> valueOperationsService;
    @DubboConsumerClient
    protected RedisOperationsService<String, String> redisOperationsService;
    @DubboConsumerClient
    protected ListOperationsService<String, String> listOperationsService;
    @DubboConsumerClient
    protected SetOperationsService<String, String> setOperationsService;
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private ConfigParameterMapper configParameterMapper;

    /**
     * 获取当前登录用户 由于core包不能引入具体的model包，所以只能返回map
     *
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getCurrentUser(String token) {
        String userId = getUserId(token);
        AdminUser adminUser = adminUserMapper.selectById(userId);
        Map<String, Object> user = Maps.newConcurrentMap();
        user.put(Constants.ADMIN_USER, adminUser);
        return user;
    }

    /**
     * @return String
     */
    @Override
    public String getTokenKey() {
        return Resources.WEB_USER_LOGIN_TOKEN;
    }

    /**
     * 登录会话有效时长
     *
     * @return
     */
    @Override
    public Long getSessionTimeOut() {
        Long sessionTimeout = 60L * 30L;
        ConfigParameter parameter = null;
        try {
            QueryWrapper<ConfigParameter> queryParameter = new QueryWrapper<>();
            queryParameter.lambda().eq(ConfigParameter::getCode, Constants.WEB_SESSION_TIMEOUT);
            parameter = configParameterMapper.selectOne(queryParameter);
        } catch (DataAccessException e) {
            return sessionTimeout;
        }
        if (CheckEmptyUtil.isNotEmpty(parameter) && CheckEmptyUtil.isNotEmpty(parameter.getValue())) {
            return Long.parseLong(parameter.getValue());
        }
        return sessionTimeout;
    }

    /**
     * 获取登录鉴权CookieName
     *
     * @return
     */
    @Override
    public String getAuthorizeCookie() {
        return Tokens.WEB_LOGIN_TOKEN;
    }

    /**
     * 获取端类型
     *
     * @return
     */
    @Override
    public ScopeType getScope() {
        return ScopeType.WEB;
    }
}
