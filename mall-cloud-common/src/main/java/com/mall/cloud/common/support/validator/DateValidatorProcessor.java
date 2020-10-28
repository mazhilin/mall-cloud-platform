package com.mall.cloud.common.support.validator;

import com.mall.cloud.common.annotation.DateValidator;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.util.Date;

/**
 * <p>封装Qicloud项目DateValidatorProcessor类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-29 00:20
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class DateValidatorProcessor implements ConstraintValidator<DateValidator, String> {
    private String dateFormat;

    @Override
    public void initialize(DateValidator constraintAnnotation) {
        this.dateFormat = constraintAnnotation.dateFormat();

    }

    /**
     * 校验逻辑的实现
     *
     * @param value 需要校验的 值
     * @return 布尔值结果
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if ("".equals(value)) {
            return true;
        }
        try {
            Date date = DateUtils.parseDate(value, dateFormat);
            return date != null;
        } catch (ParseException e) {
            return false;
        }
    }
}
