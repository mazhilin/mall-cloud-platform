package com.mall.cloud.common.persistence.controller;

/**
 * <p>封装Qicloud项目Controller类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-24 01:17
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface Controller {
    /**
     * 获取Token鉴权用户
     *
     * @param name token名称
     * @return 返回结果
     */
    String getCookie(String name);
}
