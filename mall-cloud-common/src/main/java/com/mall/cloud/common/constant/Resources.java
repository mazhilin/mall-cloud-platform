package com.mall.cloud.common.constant;

/**
 * <p>封装PivotalCloud项目常量Resources类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 20:45
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public final class Resources {
    /**
     * 用户可用资源URL list key
     */
    public static final String USER_RESOURCES_KEY = "user:resources:key:";
    /**
     * 用户资源code，用于将用户资源的code存放到redis，jsp中的auth鉴权使用。
     */
    public static final String USER_RESOURCE_CODE = "user:resource:code:";
    /**
     * 后台用户登录token
     */
    public static final String WEB_USER_LOGIN_TOKEN = "web:login:token:user:id:";
    /**
     * APP用户登录token
     */
    public static final String APP_USER_LOGIN_TOKEN = "app:login:token:user:id:";
    /**
     * 微信用户登录token
     */
    public static final String SMR_USER_LOGIN_TOKEN = "smr:login:token:user:id:";
}
