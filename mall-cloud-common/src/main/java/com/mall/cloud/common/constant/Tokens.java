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
     * 用户Token
     */
    public static final String USER_TOKEN = "user" + TOKEN_SUFFIX;
    /**
     * API应用Token
     */
    public static final String API_TOKEN = "api" + TOKEN_SUFFIX;
    /**
     * 浏览器端Token
     */
    public static final String BROWSER_TOKEN = "browser" + TOKEN_SUFFIX;
    /**
     * 移动端Token
     */
    public static final String MOBILE_TOKEN = "mobile" + TOKEN_SUFFIX;
    /**
     * 接口访问Token
     */
    public static final String ACCESS_TOKEN = "access" + TOKEN_SUFFIX;
    /**
     * 接口刷新Token
     */
    public static final String REFRESH_TOKEN = "refresh" + TOKEN_SUFFIX;
    /**
     * PC端To移动端Token
     */
    public static final String WEB_TOKEN = "web" + TOKEN_SUFFIX;
    /**
     * 移动端ToPC端Token
     */
    public static final String APP_TOKEN = "app" + TOKEN_SUFFIX;
}
