package com.mall.cloud.common.persistence.controller;

import com.mall.cloud.common.component.BaseApplicationAuthorize;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.ObjectBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
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

    @Override
    public String getCookie(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 获取当前登录用户
     * @param authorize
     * @param target
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getCurrentUser(BaseApplicationAuthorize authorize, Class<T> target) {
        if (authorize == null) {
            return null;
        }
        String token = getCookie(authorize.getAuthorizeCookie());
        if (!authorize.isLogin(token)) {
            return null;
        }
        Object object = null;
        try {
            /**
             * 由于获取的map的key和数据库的字段名是一致的（带有“_”），但是映射bean的操作必须map的key和bean的字段名一致，
             * 所以将带有“_”的key名先转成驼峰标识的。
             */
            Map<String, Object> userMap = authorize.getCurrentUser(token);
            if (target.getName().equals(Map.class.getName()) || target.getName().equals(HashMap.class.getName())) {
                return (T) userMap;
            }
            Map<String, Object> newUserMap = new HashMap<String, Object>();
            if (CheckEmptyUtil.isNotEmpty(userMap)) {
                for (Map.Entry<String, Object> en : userMap.entrySet()) {
                    if (CheckEmptyUtil.isNotEmpty(en)) {
                        String[] keySplits = en.getKey().split("_");
                        if (keySplits.length > 0) {
                            StringBuffer newKey = new StringBuffer("");
                            for (int i = 0; i < keySplits.length; i++) {
                                if (i != 0) {
                                    newKey.append(CheckEmptyUtil.upperCaseFirstChar(keySplits[i]));
                                } else {
                                    newKey.append(keySplits[i]);
                                }
                            }
                            newUserMap.put(newKey.toString(), en.getValue());
                        } else {
                            newUserMap.put(en.getKey(), en.getValue());
                        }
                    }
                }
            }
            /**
             * map映射bean
             */
            object = target.newInstance();
            if (CheckEmptyUtil.isNotEmpty(object)) {
                ObjectBeanUtil.convert(object, newUserMap);
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
