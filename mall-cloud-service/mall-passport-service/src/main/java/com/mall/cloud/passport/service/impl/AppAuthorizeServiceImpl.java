package com.mall.cloud.passport.service.impl;

import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.Resources;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.passport.api.service.*;
import com.mall.cloud.passport.service.authorize.GeneralApplicationAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目AppAuthorizeServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 21:12
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class AppAuthorizeServiceImpl extends GeneralApplicationAuthorize implements AppAuthorizeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @DubboConsumerClient
    protected ValueOperationsService<String, String> valueOperationsService;
    @DubboConsumerClient
    protected RedisOperationsService<String, String> redisOperationsService;
    @DubboConsumerClient
    protected ListOperationsService<String, String> listOperationsService;
    @DubboConsumerClient
    protected SetOperationsService<String, String> setOperationsService;

    /**
     * 获取当前登录用户 由于core包不能引入具体的model包，所以只能返回map
     *
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getCurrentUser(String token) {
        String userId = getUserId(token);
        Map<String, Object> user = jdbcTemplate.queryForMap(" select * from mall_customer_user where id =? ", userId);
        return user;
    }

    /**
     * @return String
     */
    @Override
    public String getTokenKey() {
        return Resources.APP_USER_LOGIN_TOKEN;
    }

    /**
     * 登录会话有效时长
     *
     * @return
     */
    @Override
    public Long getSessionTimeOut() {
        Long sessionTimeout = 60L * 30L;
        Map<String, Object> value = null;
        try {
            value = jdbcTemplate.queryForMap(" select value from mall_config_parameter where code=? ", Constants.APP_SESSION_TIMEOUT);
        } catch (DataAccessException e) {
            return sessionTimeout;
        }
        if (CheckEmptyUtil.isNotEmpty(value) && CheckEmptyUtil.isNotEmpty(value.get("value"))) {
            return Long.parseLong(value.get("value").toString());
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
        return Tokens.APP_LOGIN_TOKEN;
    }

    /**
     * 获取端类型
     *
     * @return
     */
    @Override
    public ScopeType getScope() {
        return ScopeType.APP;
    }

}
