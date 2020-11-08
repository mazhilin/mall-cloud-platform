package com.mall.cloud.common.constant;

/**
 * <p>封装Qicloud项目Constants类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 00:53
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public final class Constants {
    /**
     * 系统springboot默认produces格式-application/json;charset=UTF-8
     */
    public static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    /**
     * 统一字符编码格式
     */
    public static final String UNIFIED_CODE = "UTF-8";
    /**
     * 统一Redis编码前缀
     */
    public static final String REDIS_SUFFIX = "mall:cloud:";
    /**
     * 系统状态-禁用状态[0,禁用-disable]
     */
    public static final Integer DEFAULT = 0;
    /**
     * 系统状态-禁用状态[0,禁用-disable]
     */
    public static final Integer DISABLE = 0;
    /**
     * 系统状态-启用状态[1, 启用-enable]
     */
    public static final Integer ENABLE = 1;
    /**
     * 系统状态-删除状态[2, 删除-delete]
     */
    public static final Integer DELETE = 2;
    /**
     * 系统账户参数-account
     */
    public static final String ACCOUNT = "account";
    /**
     * 系统密码参数- password
     */
    public static final String PASSWORD = "password";
    /**
     * 重复密码的参数名 -confirmPassword
     */
    public static final String CONFIRM_PASSWORD = "confirmPassword";
    /**
     * 后台系统默认Session名称-userInfo
     */
    public static final String SESSION = "userInfo";
    /**
     * 系统Cookie最大时效-maxAge
     */
    public static final Integer COOKIE_MAX_AGE = 7 * 60 * 60 * 24;
    /**
     * 系统Cookie默认时效-halfHour
     */
    public static final Integer COOKIE_HALF_HOUR = 30 * 60;
    /**
     * 系统Cookie路径-path
     */
    public static final String COOKIE_PATH = "/";
    /**
     * 跳转Monitor错误url
     */
    public static final String REDIRECT_MONITOR_ERROR_URL = "/monitor/views/error.html";
    /**
     * 跳转Monitor登录url
     */
    public static final String REDIRECT_MONITOR_LOGIN_URL = "/monitor/views/login.html";
    /**
     * 跳转Monitor首页url
     */
    public static final String REDIRECT_MONITOR_INDEX_URL = "/monitor/views/index.html";
    /**
     * 跳转Monitor访问禁止页面url
     */
    public static final String REDIRECT_MONITOR_FORBID_URL = "/monitor/views/forbid.html";

    public static final String REQUEST_WITH = "X-Requested-With";
    public static final String REQUEST_HTTP = "XMLHttpRequest";
    public static final String FORWARDED_IP = "x-forwarded-for";
    public static final String PROXY_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_FORWARDED_IP = "HTTP_X_FORWARDED_FOR";
    public static final String IP_FOUR_LOCALHOST = "127.0.0.1";
    public static final String IP_SIX_LOCALHOST = "0:0:0:0:0:0:0:1";
    public static final String IP_SEPARATOR = ",";
    public static final String UNKNOWN = "unknown";
    public static final String OS_NAME = "os.name";
    /**
     * 接口请求和调用服务成功-[success-2000]
     */
    public static final Integer SUCCESS = 200;
    /**
     * 接口请求和调用服务失败-[failure-500]
     */
    public static final Integer FAILURE = 500;
    /**
     * 接口请求和调用服务TraceId-[RequestId]
     */
    public static final String REQUEST_ID = "RequestId";
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
}
