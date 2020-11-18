package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

import java.util.Arrays;

/**
 * <p>封装Qicloud项目MenuAuthType类.<br></p>
 * <p>//TODO...<br></p>
 * 菜单权限类型[0-系统授权 1-公司授权 2-员工授权 3-公共授权]
 *
 * @author Powered by marklin 2020-11-14 00:56
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum MenuAuthType implements BaseHandler {
    /**
     * 授权-公共授权-0
     */
    DEFAULT(0, "公共授权"),
    /**
     * 授权-系统管理员-1
     */
    ADMIN(1, "系统授权"),
    /**
     * 授权-公司管理员-1
     */
    COMPANY(2, "公司授权"),
    /**
     * 授权-公司员工-2
     */
    EMPLOYEE(3, "员工授权");



    /**
     * 编码-code
     */
    private final Integer code;
    /**
     * 描述-message
     */
    private final String message;


    MenuAuthType(Integer code, String message) {
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
