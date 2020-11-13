package com.mall.cloud.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * <p>封装Qicloud项目ObjectBeanUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 21:31
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public final class ObjectBeanUtil {
    public ObjectBeanUtil() {
        super();
    }

    public static synchronized void convert(Object obj, Map<String, Object> map) {
        try {
            ConvertUtils.register(new DateLocaleConverter(Locale.CHINA), Date.class);
            ConvertUtils.register(new StringConverter(null), String.class);
            ConvertUtils.register(new LongConverter(null), Long.class);
            ConvertUtils.register(new BooleanConverter(null), Boolean.class);
            ConvertUtils.register(new DoubleConverter(null), Double.class);
            ConvertUtils.register(new IntegerConverter(null), Integer.class);
            ConvertUtils.register(new FloatConverter(null), Float.class);
            ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
            BeanUtils.populate(obj, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
