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
     * jsonp默认回调函数名
     **/
    public static final String JSONP_DEFAULT_CALLBACK = "callback";

    /**
     * session数据key
     **/
    public static final String SESSION_KEY = "sessionData";

    /**
     * 用户登录Token
     **/
    public static final String LOGIN_TOKEN = "login:token:";
    /**
     * ManagerPlatform用户登录Token
     **/
    public static final String PASSPORT_MANAGER_LOGIN_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.LOGIN_TOKEN;
    /**
     * MobilePlatform用户登录Token
     **/
    public static final String PASSPORT_MOBILE_LOGIN_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.LOGIN_TOKEN;
    /**
     * MallPlatform用户登录Token
     **/
    public static final String PASSPORT_MALL_LOGIN_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.LOGIN_TOKEN;
    /**
     * BloggerPlatform用户登录Token
     **/
    public static final String PASSPORT_BLOGGER_LOGIN_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.LOGIN_TOKEN;
    /**
     * PodcastPlatform用户登录Token
     **/
    public static final String PASSPORT_PODCAST_LOGIN_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.LOGIN_TOKEN;
    /**
     * 接口API访问Token
     **/
    public static final String API_TOKEN = "api:token:";
    /**
     * ManagerPlatform接口API访问Token
     **/
    public static final String PASSPORT_MANAGER_API_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.API_TOKEN;
    /**
     * MobilePlatform接口API访问Token
     **/
    public static final String PASSPORT_MOBILE_API_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.API_TOKEN;
    /**
     * MallPlatform接口API访问Token
     **/
    public static final String PASSPORT_MALL_API_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.API_TOKEN;
    /**
     * BloggerPlatform接口API访问Token
     **/
    public static final String PASSPORT_BLOGGER_API_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.API_TOKEN;
    /**
     * PodcastPlatform接口API访问Token
     **/
    public static final String PASSPORT_PODCAST_API_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.API_TOKEN;
    /**
     * 浏览器browser访问Token
     **/
    public static final String BROWSER_TOKEN = "browser:token:";
    public static final String PASSPORT_MANAGER_BROWSER_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.BROWSER_TOKEN;
    public static final String PASSPORT_MOBILE_BROWSER_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.BROWSER_TOKEN;
    public static final String PASSPORT_MALL_BROWSER_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.BROWSER_TOKEN;
    public static final String PASSPORT_BLOGGER_BROWSER_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.BROWSER_TOKEN;
    public static final String PASSPORT_PODCAST_BROWSER_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.BROWSER_TOKEN;
    /**
     * 移动端Mobile访问Token
     **/
    public static final String MOBILE_TOKEN = "mobile:token:";
    public static final String PASSPORT_MANAGER_MOBILE_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.MOBILE_TOKEN;
    public static final String PASSPORT_MOBILE_MOBILE_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.MOBILE_TOKEN;
    public static final String PASSPORT_MALL_MOBILE_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.MOBILE_TOKEN;
    public static final String PASSPORT_BLOGGER_MOBILE_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.MOBILE_TOKEN;
    public static final String PASSPORT_PODCAST_MOBILE_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.MOBILE_TOKEN;
    /**
     * 普通Access访问Token
     **/
    public static final String ACCESS_TOKEN = "access:token:";
    public static final String PASSPORT_MANAGER_ACCESS_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.ACCESS_TOKEN;
    public static final String PASSPORT_MOBILE_ACCESS_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.ACCESS_TOKEN;
    public static final String PASSPORT_MALL_ACCESS_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.ACCESS_TOKEN;
    public static final String PASSPORT_BLOGGER_ACCESS_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.ACCESS_TOKEN;
    public static final String PASSPORT_PODCAST_ACCESS_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.ACCESS_TOKEN;
    /**
     * 刷新Refresh访问Token
     **/
    public static final String REFRESH_TOKEN = "refresh:token:";
    public static final String PASSPORT_MANAGER_REFRESH_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.REFRESH_TOKEN;
    public static final String PASSPORT_MOBILE_REFRESH_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.REFRESH_TOKEN;
    public static final String PASSPORT_MALL_REFRESH_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.REFRESH_TOKEN;
    public static final String PASSPORT_BLOGGER_REFRESH_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.REFRESH_TOKEN;
    public static final String PASSPORT_PODCAST_REFRESH_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.REFRESH_TOKEN;
    /**
     * PC端To移动端Token
     **/
    public static final String WEB_TOKEN = "web:token:";
    public static final String PASSPORT_MANAGER_WEB_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.WEB_TOKEN;
    public static final String PASSPORT_MOBILE_WEB_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.WEB_TOKEN;
    public static final String PASSPORT_MALL_WEB_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.WEB_TOKEN;
    public static final String PASSPORT_BLOGGER_WEB_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.WEB_TOKEN;
    public static final String PASSPORT_PODCAST_WEB_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.WEB_TOKEN;
    /**
     * 移动端ToPC端Token
     **/
    public static final String APP_TOKEN = "app:token:";
    public static final String PASSPORT_MANAGER_APP_TOKEN = Constants.REDIS_SUFFIX + "manager:" + Passports.APP_TOKEN;
    public static final String PASSPORT_MOBILE_APP_TOKEN = Constants.REDIS_SUFFIX + "mobile:" + Passports.APP_TOKEN;
    public static final String PASSPORT_MALL_APP_TOKEN = Constants.REDIS_SUFFIX + "mall:" + Passports.APP_TOKEN;
    public static final String PASSPORT_BLOGGER_APP_TOKEN = Constants.REDIS_SUFFIX + "blogger:" + Passports.APP_TOKEN;
    public static final String PASSPORT_PODCAST_APP_TOKEN = Constants.REDIS_SUFFIX + "podcast:" + Passports.APP_TOKEN;

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

    /**
     * 成功错误码
     **/
    public static final String SUCCESS_CODE = "0000";

    /**
     * 系统异常错误码
     **/
    public static final String ERROR_CODE = "9999";

    /**
     * 业务异常错误码
     **/
    public static final String BIZ_ERR_CODE = "1000";

    /**
     * remember me 状态
     **/
    public static final String REMEMBER_ON = "on";

    /**
     * web登录端
     **/
    public static final int LOGIN_TYPE_WEB = 1;

    /**
     * app登录端
     **/
    public static final int LOGIN_TYPE_APP = 2;

    /**
     * 匿名访问策略
     **/
    public static final String ANON_ACCESS_POLICY = "ANON_ACCESS";

    /**
     * 授权访问策略
     **/
    public static final String AUTH_ACCESS_POLICY = "AUTH_ACCESS";

    /**
     * 路由访问策略
     **/
    public static final String ROUTER_ACCESS_POLICY = "ROUTER_ACCESS";

    public static final String DEFAULT_ACCESS_POLICY = "default-access-policy";
}
