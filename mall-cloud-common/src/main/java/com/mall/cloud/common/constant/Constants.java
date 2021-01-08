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
    public static final Integer DISABLE = 0;
    /**
     * 系统状态-启用状态[1, 启用-enable]
     */
    public static final Integer ENABLE = 1;
    /**
     * 系统状态-移除状态[2, 移除-remove]
     */
    public static final Integer REMOVE = 2;
    /**
     * 系统状态-是否状态[0,否-NO]
     */
    public static final Integer NO = 0;
    /**
     * 系统状态-是否状态[1, 是-YES]
     */
    public static final Integer YES = 1;
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
     * 后台系统默认Session名称-adminUser
     */
    public static final String ADMIN_USER = "adminUser";
    /**
     * 后台系统默认Session名称-adminUser
     */
    public static final String APP_USER = "adminUser";
    /**
     * MD5加密公共盐
     */
    public static final String PUBLIC_SALT = "pivotalcloud";

    /**
     * 文件图片支持格式
     */
    public static final String[] PICTURE_FORMATS = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};
    /**
     * 视频文件支持格式
     */
    public static final String[] VIDEO_FORMATS = {".avi", ".wmv", ".mp4", ".mov", ".flv"};

    /**
     * 音频文件支持格式
     */
    public static final String[] AUDIO_FORMATS = {".wav", ".wma", ".mp3", ".acc", ".flac"};
    /**
     * 文档文件支持格式
     */
    public static final String[] DOCUMENT_FORMATS = {".docx", ".doc", ".txt", ".md", ".xls"};

    /**
     * 图片bucket名称
     */
    public static final String PICTURE = "picture";
    /**
     * 音频bucket名称
     */
    public static final String AUDIO = "audio";
    /**
     * 视频bucket名称
     */
    public static final String VIDEO = "video";
    /**
     * 文档bucket名称
     */
    public static final String DOCUMENT = "document";

    /**
     * 其他/模板bucket名称
     */
    public static final String TEMPLATE = "template";
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
     * 后台登录超时，单位秒
     */
    public static final String WEB_SESSION_TIMEOUT = "web_session_timeout";

    /**
     * 微信端登录超时，单位秒
     */
    public static final String SMR_SESSION_TIMEOUT = "smr_session_timeout";

    /**
     * app端登录超时，单位秒
     */
    public static final String APP_SESSION_TIMEOUT = "app_session_timeout";
    /**
     * dubbo 服务通用版本号
     */
    public static final String DUBBO_SERVICE_VERSION = "1.0.0";
    /**
     * dubbo 服务通用超时时间
     */
    public static final int DUBBO_SERVICE_TIMEOUT = 360000;
    /**
     * dubbo 服务通用检查状态
     */
    public static final boolean DUBBO_SERVICE_CHECK = false;
    /**
     * dubbo 服务通用重试次数
     */
    public static final int DUBBO_SERVICE_RETRIES = 1;
}
