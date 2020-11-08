package com.mall.cloud.passport.service.impl;

import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import com.mall.cloud.passport.api.service.ZSetOperationsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目ValueOperationsServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 02:14
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Component(value = "valueOperationsServiceImpl")
@Service(version = Constants.DUBBO_SERVICE_VERSION, timeout = Constants.DUBBO_TIMEOUT)
public class ValueOperationsServiceImpl <K, V> implements ValueOperationsService<K, V> {
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<K, V> valueOperations;

    @Override
    public void set(K key, V value) {
        valueOperations.set(key, value);
    }

    @Override
    public void set(K key, V value, long timeout, TimeUnit unit) {
        valueOperations.set(key, value, timeout, unit);
    }

    @Override
    public Boolean setIfAbsent(K key, V value) {
        return valueOperations.setIfAbsent(key, value);
    }

    @Override
    public void multiSet(Map<? extends K, ? extends V> map) {
        valueOperations.multiSet(map);
    }

    @Override
    public Boolean multiSetIfAbsent(Map<? extends K, ? extends V> map) {
        return valueOperations.multiSetIfAbsent(map);
    }

    @Override
    public V get(Object key) {
        V str = valueOperations.get(key);
        return str;
    }

    @Override
    public V getAndSet(K key, V value) {
        return valueOperations.getAndSet(key, value);
    }

    @Override
    public List<V> multiGet(Collection<K> keys) {
        return valueOperations.multiGet(keys);
    }

    @Override
    public Long increment(K key, long delta) {
        return valueOperations.increment(key, delta);
    }

    @Override
    public Double increment(K key, double delta) {
        return valueOperations.increment(key, delta);
    }

    @Override
    public Integer append(K key, String value) {
        return valueOperations.append(key, value);
    }

    @Override
    public String get(K key, long start, long end) {
        return valueOperations.get(key, start, end);
    }

    @Override
    public void set(K key, V value, long offset) {
        valueOperations.set(key, value, offset);
    }

    @Override
    public Long size(K key) {
        return valueOperations.size(key);
    }

    @Override
    public Boolean setBit(K key, long offset, boolean value) {
        return valueOperations.setBit(key, offset, value);
    }

    @Override
    public Boolean getBit(K key, long offset) {
        return valueOperations.getBit(key, offset);
    }

    @Override
    public RedisOperations<K, V> getOperations() {
        return valueOperations.getOperations();
    }

    @Override
    public List<Long> bitField(K arg0, BitFieldSubCommands arg1) {
        return valueOperations.bitField(arg0, arg1);
    }

    @Override
    public Long decrement(K arg0) {
        return valueOperations.decrement(arg0);
    }

    @Override
    public Long decrement(K arg0, long arg1) {
        return valueOperations.decrement(arg0, arg1);
    }

    @Override
    public Long increment(K arg0) {
        return valueOperations.increment(arg0);
    }

    @Override
    public Boolean setIfAbsent(K arg0, V arg1, long arg2, TimeUnit arg3) {
        return valueOperations.setIfAbsent(arg0, arg1, arg2, arg3);
    }

    @Override
    public Boolean setIfPresent(K arg0, V arg1) {
        return valueOperations.setIfPresent(arg0, arg1);
    }

    @Override
    public Boolean setIfPresent(K arg0, V arg1, long arg2, TimeUnit arg3) {
        return valueOperations.setIfPresent(arg0, arg1, arg2, arg3);
    }
}
