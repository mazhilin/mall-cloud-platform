package com.mall.cloud.common.utils;

import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>封装Qicloud项目CheckEmptyUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 23:08
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class CheckEmptyUtil {
    /**
     * 判断一个参数是否为空。判断的时候根据不同的类型，会有不同的判断标准。
     *
     * @param object 待校验参数
     * @return 如果object为null，不进行类型判断，直接返回为true.
     * 如果object不为null，则针对不同的class，分别进行判断。
     * String: trim后length为０的时候返回true.
     * List:　返回list.isEmpty().
     * Map: 返回map.isEmpty().
     * Array: 如果length为０，返回true.
     * Set:　返回set.isEmpty().
     * CharSequence: length为0或者toString后trim为空，则返回true.
     * @author Marklin
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            String str = (String) object;
            if (str.trim().length() == 0) {
                return true;
            }
        }

        if (object instanceof CharSequence) {
            CharSequence cs = (CharSequence) object;
            if (cs.length() == 0) {
                return true;
            }

            if (cs.toString().trim().length() == 0) {
                return true;
            }
        }

        if (object instanceof List<?>) {
            List<?> list = (List<?>) object;
            return list.isEmpty();
        }

        if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            if (array.length == 0) {
                return true;
            }
        }

        if (object instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) object;
            return map.isEmpty();
        }

        if (object instanceof Set<?>) {
            Set<?> set = (Set<?>) object;
            return set.isEmpty();
        }

        return false;
    }

    /**
     * 判断对象是否非空，其实现为isEmpty取反
     *
     * @param object 待校验参数
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 如果传入的所有参数中任意一个为空，则返回true.
     *
     * @param objects 待校验参数
     * @return
     */
    public static boolean isOrEmpty(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }

        for (Object object : objects) {
            if (isEmpty(object)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param objects 对象数组
     * @return boolean
     * @apiNote 判断多个
     * @author yepk
     * @date 2019/5/7 9:49
     */
    public static boolean isOrNotEmpty(Object... objects) {
        if (objects == null || objects.length == 0) {
            return false;
        }
        for (Object object : objects) {
            if (isNotEmpty(object)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAndEmpty(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        for (Object object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAndNotEmpty(Object... objects) {
        if (objects == null || objects.length == 0) {
            return false;
        }
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }
}
