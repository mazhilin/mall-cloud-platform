package com.mall.cloud.common.component;

import com.mall.cloud.common.constant.ScopeType;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.List;
import java.util.Map;

/**
 * <p>封装Qicloud项目BaseApplicationAuthorize类.<br></p>
 * <p>系统应用鉴权<br></p>
 *
 * @author Powered by marklin 2020-11-12 20:05
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface BaseApplicationAuthorize extends ApplicationListener<ContextClosedEvent> {
    /**
     * 判断用户是否登录
     *
     * @param token 用户Token
     * @return 返回结果
     */
    boolean isLogin(String token);

    /**
     * 获取当前登录用户
     *
     * @return
     */
    Map<String, Object> getCurrentUser(String token);

    /**
     * 删除鉴权信息
     *
     * @param token
     *            登录会话token
     */
    void deleteAuthorize(String token);

    /**
     * 设置用户鉴权信息
     *
     * @param userId
     * @param token
     */
    void setAuthorize(String userId, String token);

    /**
     * 根据token获取userid
     *
     * @param token
     * @return userid
     */
    String getUserId(String token);

    /**
     *
     * @return String
     */
    String getTokenKey();

    /**
     * 登录会话有效时长
     *
     * @return
     */
    Long getSessionTimeOut();

    /**
     * 获取登录鉴权CookieName
     *
     * @return
     */
    String getAuthorizeCookie();

    /**
     * 删除用户资源权限
     */
    void deleteResources(String userId);

    /**
     * 存储用户资源到redis中
     */
    void setResources(List<String> resourceList, String userId);

    /**
     * 获取端类型
     *
     * @return
     */
    ScopeType getScope();

    /**
     * 获取用户redis资源key
     *
     * @return
     */
    String getResources(String userId);

    /**
     * 后台用户登出
     *
     * @param userId 用户id
     * @return 返回结果
     */
    boolean deleteResourceCode(String userId);
}
