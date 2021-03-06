package com.mall.cloud.common.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>封装Qicloud项目ApplicationThreadLocal类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 22:11
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Component
public class ApplicationThreadLocal {
    private final ThreadLocal<Map<String, Object>> ttl = new TransmittableThreadLocal<>();

    public void setValue(String key, Object value) {
        Map<String, Object> map = get();
        if (map == null) {
            map = Maps.newConcurrentMap();
        }
        map.put(key, value);
        ttl.set(map);
    }

    public Map<String, Object> get() {
        return ttl.get();
    }

    public void clean() {
        ttl.remove();
    }

}
