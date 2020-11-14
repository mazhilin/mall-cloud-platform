package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.Resources;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.ConfigParameter;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.mapper.system.ConfigParameterMapper;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.passport.api.service.*;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目AdminAuthorizeServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 20:53
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class AdminAuthorizeServiceImpl extends BaseServerService implements AdminAuthorizeService {
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
     * 判断用户是否登录
     *
     * @param token
     * @return
     */
    @Override
    public boolean isLogin(String token) {
        if (CheckEmptyUtil.isNotEmpty(token) && CheckEmptyUtil.isNotEmpty(getUserId(token))) {
            return true;
        }
        return false;
    }

    /**
     * 删除鉴权信息
     *
     * @param token 登录会话token
     */
    @Override
    public synchronized void deleteAuthorize(String token) {
        if (StringUtils.hasText(token)) {
            redisOperationsService.delete(getTokenKey() + token);
        }
    }

    /**
     * 设置用户鉴权信息
     *
     * @param userId
     * @param token
     */
    @Override
    public synchronized void setAuthorize(String userId, String token) {
        // 将用户登录token放到redis中，对应用户id，用于记录用户的登录状态。默认30分钟有效。
        valueOperationsService.set(getTokenKey() + token, userId, getSessionTimeOut(), TimeUnit.SECONDS);
    }

    /**
     * 根据token获取userid
     *
     * @param token
     * @return userid
     */
    @Override
    public synchronized String getUserId(String token) {
        if (token == null) {
            return null;
        }
        String userId = valueOperationsService.get(getTokenKey() + token);
        return CheckEmptyUtil.isNotEmpty(userId) ? userId : null;
    }

    /**
     * 删除用户资源权限
     *
     * @param userId 直接删除USER_RESOURCES_KEY + scope + userId
     */
    @Override
    public void deleteResources(String userId) {
        redisOperationsService.delete(Resources.USER_RESOURCES_KEY + getScope().code() + ":" + userId);
    }

    /**
     * 后台用户登出，需要删掉jsp的auth标签的redis的code。
     *
     * @param userId
     * @return
     */
    @Override
    public boolean deleteResourceCode(String userId) {
        return redisOperationsService.delete(Resources.USER_RESOURCE_CODE + userId);
    }

    /**
     * 存储用户资源到redis中
     *
     * @param resourceList
     * @param userId
     */
    @Override
    public void setResources(List<String> resourceList, String userId) {
        setUserResourcesReal(Resources.USER_RESOURCES_KEY + getScope().code() + ":" + userId, resourceList, userId);
    }


    /**
     * 获取用户redis资源key
     *
     * @param userId
     * @return
     */
    @Override
    public String getResources(String userId) {
        return Resources.USER_RESOURCES_KEY + getScope().code() + ":" + userId;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }

    /**
     * 存储用户资源到redis中。将用户资源以USER_RESOURCES_KEY + scope + userId为key，放到redis set中
     */
    public void setUserResourcesReal(String key, List<String> resourceList, String userId) {
        if (CheckEmptyUtil.isNotEmpty(resourceList)) {
            List<String> resourceListNew = new ArrayList<String>();
            for (String urlJoinStr : resourceList) {
                if (CheckEmptyUtil.isNotEmpty(urlJoinStr)) {
                    for (String url : urlJoinStr.split(";")) {
                        // 因为鉴权拦截器拦截的url都是/开头的，所以这里的url也必须以/开头保存。
                        if (url.startsWith("/")) {
                            resourceListNew.add(url);
                        } else {
                            resourceListNew.add("/" + url);
                        }
                    }
                }
            }
            String[] resourceListArr = new String[resourceListNew.size()];
            resourceListArr = resourceListNew.toArray(resourceListArr);
            setOperationsService.add(key, resourceListArr);
        }
    }

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
        user.put("adminUser", adminUser);
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
