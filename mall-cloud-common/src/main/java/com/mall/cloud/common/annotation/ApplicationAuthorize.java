package com.mall.cloud.common.annotation;

import com.mall.cloud.common.constant.PlatformType;
import com.mall.cloud.common.constant.ScopeType;

import java.lang.annotation.*;

/**
 * <p>封装Qicloud项目ApplicationAuthorize类.<br></p>
 * <p>系统统一Authorize鉴权自定义注解ApplicationAuthorize<br></p>
 *
 * @author Powered by marklin 2020-10-28 10:26
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApplicationAuthorize {
    /**
     * 是否必须
     */
    boolean required() default true;
    /**
     * 鉴权用户是否登录
     *
     * @return
     */
    boolean authorizeLogin() default true;
    /**
     * 鉴权url资源是否有权限可调用
     *
     * @return
     */
    boolean authorizeResources() default true;
    /**
     * 平台模块-platform[web-Web应用平台 app-APP平台 smr-小程序平台]
     */
    PlatformType platform() default PlatformType.WEB;
    /**
     * 作用域-scope鉴权范围，指定端范围，默认为为后台。指定端后，登录用户只鉴权改端的登录用户，资源只鉴权该端的资源。
     */
    ScopeType scope() default ScopeType.CONSOLE;
    /**
     * 是否持久化请求参数-persistence
     */
    boolean persistence() default true;
}