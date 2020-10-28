package com.mall.cloud.common.support.validator;

import com.mall.cloud.common.annotation.IdCardValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p>封装Qicloud项目IdCardValidatorProcessor类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-29 00:35
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class IdCardValidatorProcessor implements ConstraintValidator<IdCardValidator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    @Override
    public void initialize(IdCardValidator constraintAnnotation) {

    }
}
