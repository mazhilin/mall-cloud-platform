package com.mall.cloud.common.persistence.controller;

import com.google.common.collect.Maps;
import com.mall.cloud.common.component.BaseApplicationAuthorize;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.ObjectBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>封装Qicloud项目BaseController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-24 01:17
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public abstract class BaseController implements Controller {
    protected static final transient Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * 获取Token鉴权用户
     *
     * @param name token名称
     * @return 返回结果
     */
    @Override
    public String getCookie(String name) {
        // 从请求头获取AuthorizeToken
        String token = request.getHeader(name);
        // 从header中获取token
        if (CheckEmptyUtil.isEmpty(token) && CheckEmptyUtil.isNotEmpty(request.getHeader(name))) {
            token = request.getParameter(name);
        }
        logger.info("token-value::{}", token);
        return token;
    }

    /**
     * 获取当前登录用户
     *
     * @param authorize 用户鉴权Token
     * @param <T>       返回类型
     * @return 返回结果
     */
    @SuppressWarnings("unchecked")
    public <T> T getCurrentUser(BaseApplicationAuthorize authorize, Class<T> target) {
        if (authorize == null) {
            return null;
        }
        String authorizeCookie = authorize.getAuthorizeCookie();
        logger.info("authorizeCookie:: {}", authorizeCookie);
        String token = getCookie(authorizeCookie);
        if (!authorize.isLogin(token)) {
            return null;
        }
        Object object = null;
        try {
            Map<String, Object> authorizeDataMap = authorize.getCurrentUser(token);
            if (CheckEmptyUtil.isNotEmpty(authorizeDataMap)) {
                if (target.getName().equals(Map.class.getName())
                        || target.getName().equals(HashMap.class.getName())) {
                    return (T) authorizeDataMap;
                }
                Map<String, Object> builderDataMap = Maps.newConcurrentMap();
                for (Map.Entry<String, Object> entry : authorizeDataMap.entrySet()) {
                    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                    if (CheckEmptyUtil.isNotEmpty(entry)) {
                        builderDataMap.put(entry.getKey(), entry.getValue());
                        System.out.println(builderDataMap);
                    }
                }
                object = target.newInstance();
                if (CheckEmptyUtil.isNotEmpty(object)) {
                    ObjectBeanUtil.convert(object, builderDataMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationServerException(e);
        }
        if (CheckEmptyUtil.isNotEmpty(object)) {
            return (T) object;
        }
        return null;
    }
}
