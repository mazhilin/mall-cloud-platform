package com.mall.cloud.passport.service.authorize;


import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.component.BaseApplicationAuthorize;
import com.mall.cloud.common.constant.Resources;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.passport.api.service.ListOperationsService;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.SetOperationsService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目GeneralApplicationAuthorize类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 20:28
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public abstract class GeneralApplicationAuthorize implements BaseApplicationAuthorize {
    @DubboConsumerClient
    protected ValueOperationsService<String, String> valueOperationsService;
    @DubboConsumerClient
    protected RedisOperationsService<String, String> redisOperationsService;
    @DubboConsumerClient
    protected ListOperationsService<String, String> listOperationsService;
    @DubboConsumerClient
    protected SetOperationsService<String, String> setOperationsService;

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
            redisOperationsService.delete(getTokenKey()+token);
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
        valueOperationsService.set(getTokenKey()+token, userId, getSessionTimeOut(), TimeUnit.SECONDS);
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
        return valueOperationsService.get(getTokenKey()+token);
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

}
