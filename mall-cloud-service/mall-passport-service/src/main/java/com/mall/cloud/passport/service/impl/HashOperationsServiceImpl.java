package com.mall.cloud.passport.service.impl;

import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.passport.api.service.HashOperationsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>封装Qicloud项目HashOperationsServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 01:52
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Service(version = Constants.DUBBO_SERVICE_VERSION, timeout = Constants.DUBBO_TIMEOUT)
@Component
public class HashOperationsServiceImpl<H, HK, HV> implements HashOperationsService<H, HK, HV> {
    @Resource(name = "stringRedisTemplate")
    private HashOperations<H, HK, HV> hashOperations;

    @Override
    public Long delete(H key, Object... hashKeys) {
        return hashOperations.delete(key, hashKeys);
    }

    @Override
    public Boolean hasKey(H key, Object hashKey) {
        return hashOperations.hasKey(key, hashKey);
    }

    @Override
    public HV get(H key, Object hashKey) {
        return hashOperations.get(key, hashKey);
    }

    @Override
    public List<HV> multiGet(H key, Collection<HK> hashKeys) {
        return hashOperations.multiGet(key, hashKeys);
    }

    @Override
    public Long increment(H key, HK hashKey, long delta) {
        return hashOperations.increment(key, hashKey, delta);
    }

    @Override
    public Double increment(H key, HK hashKey, double delta) {
        return hashOperations.increment(key, hashKey, delta);
    }

    @Override
    public Set<HK> keys(H key) {
        return hashOperations.keys(key);
    }

    @Override
    public Long size(H key) {
        return hashOperations.size(key);
    }

    @Override
    public void putAll(H key, Map<? extends HK, ? extends HV> m) {
        hashOperations.putAll(key, m);
    }

    @Override
    public void put(H key, HK hashKey, HV value) {
        hashOperations.put(key, hashKey, value);
    }

    @Override
    public Boolean putIfAbsent(H key, HK hashKey, HV value) {
        return hashOperations.putIfAbsent(key, hashKey, value);
    }

    @Override
    public List<HV> values(H key) {
        return hashOperations.values(key);
    }

    @Override
    public Map<HK, HV> entries(H key) {
        return hashOperations.entries(key);
    }

    @Override
    public Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options) {
        return hashOperations.scan(key, options);
    }

    @Override
    public RedisOperations<H, ?> getOperations() {
        return hashOperations.getOperations();
    }

    @Override
    public Long lengthOfValue(H arg0, HK arg1) {
        return hashOperations.lengthOfValue(arg0, arg1);
    }
}
