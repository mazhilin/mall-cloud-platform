package com.mall.cloud.common.persistence.controller;

import com.mall.cloud.common.utils.CheckEmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


}
