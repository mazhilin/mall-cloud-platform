package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

import java.util.Arrays;

/**
 * <p>封装Qicloud项目UserType类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 23:45
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum UserType implements BaseHandler {
    /**
     * 系统管理员-0
     */
    ADMIN(0, "系统管理员"),
    /**
     * 公司管理员-1
     */
    COMPANY(1, "公司管理员"),
    /**
     * 公司员工-2
     */
    EMPLOYEE(2, "公司员工");
    /**
     * 编码-code
     */
    private final Integer code;
    /**
     * 描述-message
     */
    private final String message;


    UserType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static UserType get(int key) {
        UserType[] values = UserType.values();
        return Arrays.stream(values).filter(item -> item.code() == key).findFirst().orElse(null);
    }

    /**
     * code 编码
     *
     * @return 返回编码
     */
    @Override
    public Integer code() {
        return code;
    }

    /**
     * message 信息
     *
     * @return 返回信息
     */
    @Override
    public String message() {
        return message;
    }
}
