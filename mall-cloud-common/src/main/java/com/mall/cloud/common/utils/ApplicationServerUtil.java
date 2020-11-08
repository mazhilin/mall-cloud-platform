package com.mall.cloud.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mall.cloud.common.constant.Passports;
import com.mall.cloud.common.exception.ApplicationServerException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>封装Qicloud项目ApplicationServerUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 10:46
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@UtilityClass
public class ApplicationServerUtil extends WebUtils {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationServerUtil.class);
    private final String BASIC_ = "Basic ";

    private final String UNKNOWN = "unknown";

    /**
     * 请求地址结束标志 *
     */
    private static final String PARAM_END = "/";

    /**
     * 请求参数结束标志 *
     */
    private static final char PARAM_CHAR = '?';

    /**
     * 请求参数连接标志 *
     */
    private static final char PARAM_APPEND = '&';

    /**
     * 请求参数等于标志 *
     */
    private static final char PARAM_EQUALS = '=';


    /**
     * 判断是否ajax请求 spring ajax 返回含有 ResponseBody 或者 RestController注解
     *
     * @param handlerMethod HandlerMethod
     * @return 是否ajax请求
     */
    public boolean isBody(HandlerMethod handlerMethod) {
        ResponseBody responseBody = ParameterServerUtil.getAnnotation(handlerMethod, ResponseBody.class);
        return responseBody != null;
    }

    /**
     * 读取cookie
     *
     * @param name cookie name
     * @return cookie value
     */
    public String getCookieVal(String name) {
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        Assert.notNull(request, "request from RequestContextHolder is null");
        return getCookieVal(request, name);
    }

    /**
     * 读取cookie
     *
     * @param request HttpServletRequest
     * @param name    cookie name
     * @return cookie value
     */
    public String getCookieVal(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 清除 某个指定的cookie
     *
     * @param response HttpServletResponse
     * @param key      cookie key
     */
    public void removeCookie(HttpServletResponse response, String key) {
        setCookie(response, key, null, 0);
    }

    /**
     * 设置cookie
     *
     * @param response        HttpServletResponse
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds maxage
     */
    public void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return {HttpServletRequest}
     */
    public HttpServletRequest getRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } catch (IllegalStateException e) {
            return null;
        }
    }

    /**
     * 获取 HttpServletResponse
     *
     * @return {HttpServletResponse}
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 返回json
     *
     * @param response HttpServletResponse
     * @param result   结果对象
     */
    public void renderJson(HttpServletResponse response, Object result) {
        renderJson(response, result, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 返回json
     *
     * @param response    HttpServletResponse
     * @param result      结果对象
     * @param contentType contentType
     */
    public void renderJson(HttpServletResponse response, Object result, String contentType) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
        try (PrintWriter out = response.getWriter()) {
            out.append(JSONUtil.toJsonStr(result));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取ip
     *
     * @return {String}
     */
    public String getIP() {
        return getIP(ApplicationServerUtil.getRequest());
    }

    /**
     * 获取ip
     *
     * @param request HttpServletRequest
     * @return {String}
     */
    public String getIP(HttpServletRequest request) {
        Assert.notNull(request, "HttpServletRequest is null");
        String ip = request.getHeader("X-Requested-For");
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return StrUtil.isBlank(ip) ? null : ip.split(",")[0];
    }

    /**
     * 解析 client id
     *
     * @param header
     * @param defVal
     * @return 如果解析失败返回默认值
     */
    public String extractClientId(String header, final String defVal) {

        if (header == null || !header.startsWith(BASIC_)) {
            logger.debug("请求头中client信息为空: {}", header);
            return defVal;
        }
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            logger.debug("Failed to decode basic authentication token: {}", header);
            return defVal;
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(":");

        if (delim == -1) {
            logger.debug("Invalid basic authentication token: {}", header);
            return defVal;
        }
        return token.substring(0, delim);
    }

    /**
     * 从请求头中解析 client id
     *
     * @param header
     * @return
     */
    public Optional<String> extractClientId(String header) {
        return Optional.ofNullable(extractClientId(header, null));
    }

    /**
     * 从request 获取CLIENT_ID
     *
     * @return
     */
    public String getClientId(String header) {
        String clientId = extractClientId(header, null);
        if (clientId == null) {
            throw new ApplicationServerException("Invalid basic authentication token");
        }
        return clientId;
    }

    /**
     * 请求链接拼接参数
     *
     * @param requestUrl 请求地址
     * @param names      拼接参数名数组
     * @param vals       拼接参数值数组
     * @return
     */
    public static String requestAppendParam(String requestUrl, String[] names, Object[] vals) {
        StringBuffer sb = new StringBuffer(requestUrl);
        if (requestUrl.endsWith(PARAM_END)) {
            sb.deleteCharAt(requestUrl.length() - 1);
        }
        if (requestUrl.indexOf(PARAM_CHAR) > -1) {
            sb.append(PARAM_APPEND);
        } else {
            sb.append(PARAM_CHAR);
        }
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(PARAM_EQUALS).append(vals[i]);
            if (i != (names.length - 1)) {
                sb.append(PARAM_APPEND);
            }
        }
        return sb.toString();
    }

    /**
     * 是否为ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String header = "x-requested-with", httpRequest = "XMLHttpRequest";
        return Objects.nonNull(request.getHeader(header))
                && request.getHeader(header).equalsIgnoreCase(httpRequest);
    }

    /**
     * 页面输出
     *
     * @param response
     * @param content
     */
    public static void write(HttpServletResponse response, String content) {
        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0L);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(content);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回jsonp
     *
     * @param callback
     * @param json
     * @return
     */
    public static String jsonp(String callback, String json) {
        return callback + "(" + json + ")";
    }

    /**
     * 重定向地址
     *
     * @param redirectUrl
     * @return
     */
    public static String redirect(String redirectUrl) {
        return "redirect:".concat(redirectUrl);
    }

    /**
     * 重定向地址
     *
     * @param targetUrl
     * @return
     */
    public static String html(String targetUrl) {
        return targetUrl.concat(".html");
    }

    /**
     * 参数编码
     *
     * @param text
     * @return
     */
    public static String encode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.info("参数编码异常：", e);
        }
        return "";
    }

    /**
     * 参数解码
     *
     * @param text
     * @return
     */
    public static String decode(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.info("参数编码异常：", e);
        }
        return "";
    }

    /**
     * 获取原始请求完整路径
     *
     * @param request
     * @return
     */
    public static String getReqUrl(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (Objects.isNull(queryString)) {
            return request.getRequestURL().toString();
        }
        return request.getRequestURL().append(PARAM_CHAR).append(queryString).toString();
    }

    /**
     * 构造登录回调重定向地址
     *
     * @param baseUrl
     * @param ticket
     * @param targetUrl
     * @return
     */
    public static String getAuthUrl(String baseUrl, String ticket, String targetUrl) {
        return ApplicationServerUtil.requestAppendParam(
                baseUrl,
                new String[]{Passports.TICKET_KEY, Passports.TARGET_KEY},
                new Object[]{ApplicationServerUtil.encode(ticket), ApplicationServerUtil.encode(targetUrl)});
    }

    /**
     * 构造登录请求重定向地址
     *
     * @param baseUrl
     * @param serviceUrl
     * @param targetUrl
     * @return
     */
    public static String getRedirectUrl(String baseUrl, String serviceUrl, String targetUrl) {
        return ApplicationServerUtil.requestAppendParam(
                baseUrl,
                new String[]{Passports.REDIRECT_KEY, Passports.TARGET_KEY},
                new Object[]{ApplicationServerUtil.encode(serviceUrl), ApplicationServerUtil.encode(targetUrl)});
    }


}
