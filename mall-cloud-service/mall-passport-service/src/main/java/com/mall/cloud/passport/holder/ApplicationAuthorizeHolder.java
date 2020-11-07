package com.mall.cloud.passport.holder;


import com.google.common.collect.Maps;
import com.mall.cloud.common.core.passport.Authorize;
import com.mall.cloud.passport.api.service.ListOperationsService;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.SetOperationsService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目ApplicationAuthorizeHodler类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 15:09
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public abstract class ApplicationAuthorizeHolder implements Authorize {
    /**
     * 用户资源code，用于将用户资源的code存放到redis，jsp中的auth鉴权使用。
     */
    public static final String USER_RESOURCE_CODE = "user_resource_code_";
    /**
     * 用户可用资源URL list key
     */
    public final String USER_RESOURCES_KEY = "user_resources_";
    @Reference
    protected ValueOperationsService<String, String> valueOperationsService;
    @Reference
    protected RedisOperationsService<String, String> redisOperationsService;
    @Reference
    protected ListOperationsService<String, String> listOperationsService;
    @Reference
    protected SetOperationsService<String, String> setOperationsService;

    @Override
    public boolean isLogin(String token) {
        if (Objects.nonNull(token) && Objects.nonNull(getUserId(token))) {
            return true;
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }

    /**
     * 删除用户资源，直接删除USER_RESOURCES_KEY + scope + userId
     */
    @Override
    public void deleteUserResources(String userId) {
        redisOperationsService.delete(USER_RESOURCES_KEY + getScope().code() + "_" + userId);
    }

    @Override
    public boolean deleteResourceCode(String userId) {
        return redisOperationsService.delete(USER_RESOURCE_CODE + userId);
    }

    @Override
    public synchronized void deleteAuthorizeInfo(String token) {
        if (StringUtils.hasText(token)) {
            redisOperationsService.delete(token + getTokenKey());
        }
    }

    @Override
    public synchronized void setAuthorizeInfo(String user_id, String token) {
        // 将用户登录token放到redis中，对应用户id，用于记录用户的登录状态。默认30分钟有效。
        valueOperationsService.set(token + getTokenKey(), user_id, getSessionTimeOut(), TimeUnit.SECONDS);
    }

    @Override
    public synchronized String getUserId(String token) {
        if (token == null) {
            return null;
        }
        return valueOperationsService.get(token + getTokenKey());
    }

    /**
     * 存储用户资源到redis中。将用户资源以USER_RESOURCES_KEY + scope + userId为key，放到redis set中
     */
    public void setUserResourcesReal(String key, List<String> resourceList, String userId) {
        if (Objects.nonNull(resourceList)) {
            List<String> resourceListNew = new ArrayList<String>();
            for (String urlJoinStr : resourceList) {
                if (Objects.nonNull(urlJoinStr)) {
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

    @Override
    public String getResourceKey(String userId) {
        return USER_RESOURCES_KEY + getScope().code() + "_" + userId;
    }

    /**
     * 存储用户资源到redis中。将用户资源以USER_RESOURCES_KEY + scope + userId为key，放到redis set中
     */
    @Override
    public void setUserResources(List<String> resourceList, String userId) {
        setUserResourcesReal(USER_RESOURCES_KEY + getScope().code() + "_" + userId, resourceList, userId);
    }

    @Override
    public Map<String, Object> getCurrentUser(String token) {
        String userId = getUserId(token);
        Map<String, Object> stringObjectMap = Maps.newConcurrentMap();
        stringObjectMap.put("x", userId);
        return stringObjectMap;
    }
}
