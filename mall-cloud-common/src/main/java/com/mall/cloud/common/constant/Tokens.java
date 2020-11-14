package com.mall.cloud.common.constant;

/**
 * <p>封装Qicloud项目Tokens类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 00:51
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public final class Tokens {
    /** 统一设置token有效时间为30天[60 * 60 * 24 * 30L] */
    public static final Long TOKEN_TTL_TIME = 60 * 60 * 24 * 30L;
    /**
     * 设置全局Token统一Token后缀
     */
    public static final String TOKEN_SUFFIX = "_token";
    /**
     * API应用Token
     */
    public static final String API_TOKEN = "api" + TOKEN_SUFFIX;
    /**
     * 后台管理登录token
     */
    public static final String WEB_LOGIN_TOKEN = "web_login_token";
    /**
     * 移动APP登录token
     */
    public static final String APP_LOGIN_TOKEN = "app_login_token";
    /**
     * 微信登录token
     */
    public static final String SMR_LOGIN_TOKEN = "smr_login_token";
    /**
     * 静态用户登录token
     */
    public static final String USER_STATIC_TOKEN = "user_static_";
}
