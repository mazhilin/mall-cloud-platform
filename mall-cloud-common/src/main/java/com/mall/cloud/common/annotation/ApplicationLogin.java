package com.mall.cloud.common.annotation;

import com.mall.cloud.common.constant.PlatformType;
import com.mall.cloud.common.constant.ScopeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>封装Qicloud项目ApplicationLogin类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 10:26
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationLogin {
    boolean required() default true;

    /**
     * 作用域-scope
     */
    ScopeType scope() default ScopeType.CONSOLE;

    /**
     * 平台模块-platform
     */
    PlatformType platform() default PlatformType.WEB;

    /**
     * 是否持久化请求参数-persistence
     */
    boolean persistence() default true;
}
