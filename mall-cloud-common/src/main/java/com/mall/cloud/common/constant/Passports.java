package com.mall.cloud.common.constant;

/**
 * <p>封装Qicloud项目Passports类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 00:54
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public final class Passports {
    /**
     * 登录成功session参数
     **/
    public static final String SESSION_PARAM_KEY = "sessionId";
    /**
     * APP端授权参数
     **/
    public static final String TOKEN_PARAM_KEY = "token";
    /**
     * cookie过期时间：默认2小时 60*60*24*7
     **/
    public static final int COOKIE_MAX_AGE = 604800;

    /**
     * cookie保存路径：默认根路径
     **/
    public static final String COOKIE_PATH = "/";

    /**
     * redis默认过期时间：默认30分钟 60*30
     **/
    public static final int REDIS_EXPIRE_TIME = 1800;

    /**
     * 一次性票据过期时间：默认20s
     **/
    public static final int TICKET_EXPIRE_TIME = 20;
    /**
     * 登录地址
     **/
    public static final String LOGIN_URL = "/login";

    /**
     * 登出地址
     **/
    public static final String LOGOUT_URL = "/logout";

    /**
     * 注册地址
     **/
    public static final String REGISTER_URL = "/register";

    /**
     * 校验地址
     **/
    public static final String AUTHORIZE_STATUS_URL = "/authorizeStatus";

    /**
     * 登录成功回调地址
     **/
    public static final String AUTHORIZE_URL = "/authorize";

    /**
     * 刷新session过期时间地址
     **/
    public static final String REFRESH_SESSION_URL = "/refreshSession";

    /**
     * 删除session地址
     **/
    public static final String REMOVE_SESSION_URL = "/removeSession";
    /**
     * 刷新Token过期时间地址
     **/
    public static final String REFRESH_TOKEN_URL = "/refreshToken";

    /**
     * 删除session地址
     **/
    public static final String REMOVE_TOKEN_URL = "/removeToken";
    /**
     * ticket校验地址
     **/
    public static final String TICKET_VALID_URL = "/validateTicket";

    /**
     * 重定向地址参数名称
     **/
    public static final String REDIRECT_KEY = "service";

    /**
     * 目标地址参数
     **/
    public static final String TARGET_KEY = "targetUrl";

    /**
     * 授权码对象
     **/
    public static final String TICKET_KEY = "ticket";
    /**
     * 服务端登录放行标志
     **/
    public static final String GATEWAY_KEY = "gateway";
}
