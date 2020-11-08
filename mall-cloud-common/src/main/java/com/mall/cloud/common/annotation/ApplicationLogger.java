package com.mall.cloud.common.annotation;

import com.mall.cloud.common.constant.OperationType;
import com.mall.cloud.common.constant.PlatformType;
import com.mall.cloud.common.constant.ScopeType;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * <p>封装Qicloud项目ApplicationLogger类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 10:24
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApplicationLogger {
    /**
     * 模块
     */
    String title() default StringUtils.EMPTY;
    /**
     * 操作值-value
     */
    String method() default StringUtils.EMPTY;

    /**
     * 操作描述-description
     */
    String desc() default StringUtils.EMPTY;

    /**
     * 操作类型-type
     */
    OperationType type() default OperationType.ADD;

    /**
     * 作用域-scope
     */
    ScopeType scope() default ScopeType.WEB;
}
