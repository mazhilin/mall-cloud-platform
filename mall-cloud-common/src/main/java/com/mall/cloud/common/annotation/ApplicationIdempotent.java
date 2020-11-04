package com.mall.cloud.common.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.*;

/**
 * <p>封装Qicloud项目ApplicationIdempotent类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 09:26
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApplicationIdempotent {
}
