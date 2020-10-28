package com.mall.cloud.common.annotation;

import com.mall.cloud.common.support.validator.IdCardValidatorProcessor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 封装Qicloud项目IdCard类.<br>
 *
 * <p>//TODO...<br>
 *
 * @author Powered by marklin 2020-10-29 00:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.
 * <br>
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IdCardValidatorProcessor.class})
public @interface IdCardValidator {
    String message() default "身份证号码不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
