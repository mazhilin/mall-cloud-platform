package com.mall.cloud.passport.service.impl;

import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.passport.api.service.ListOperationsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目ListOperationsServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 01:55
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Service(version = Constants.DUBBO_SERVICE_VERSION, timeout = Constants.DUBBO_TIMEOUT)
@Component
public class ListOperationsServiceImpl <K, V> implements ListOperationsService<K, V> {
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<K, V> valueOperations;

    @Override
    public List<V> range(K key, long start, long end) {
        return valueOperations.getOperations().opsForList().range(key, start, end);
    }

    @Override
    public void trim(K key, long start, long end) {
        valueOperations.getOperations().opsForList().trim(key, start, end);
    }

    @Override
    public Long size(K key) {
        return valueOperations.getOperations().opsForList().size(key);
    }

    @Override
    public Long leftPush(K key, V value) {
        return valueOperations.getOperations().opsForList().leftPush(key, value);
    }

    @Override
    public Long leftPushAll(K key, V... values) {
        return valueOperations.getOperations().opsForList().leftPushAll(key, values);
    }

    @Override
    public Long leftPushAll(K key, Collection<V> values) {
        return valueOperations.getOperations().opsForList().leftPushAll(key, values);
    }

    @Override
    public Long leftPushIfPresent(K key, V value) {
        return valueOperations.getOperations().opsForList().leftPushIfPresent(key, value);
    }

    @Override
    public Long leftPush(K key, V pivot, V value) {
        return valueOperations.getOperations().opsForList().leftPush(key, pivot, value);
    }

    @Override
    public Long rightPush(K key, V value) {
        return valueOperations.getOperations().opsForList().rightPush(key, value);
    }

    @Override
    public Long rightPushAll(K key, V... values) {
        return valueOperations.getOperations().opsForList().rightPushAll(key, values);
    }

    @Override
    public Long rightPushAll(K key, Collection<V> values) {
        return valueOperations.getOperations().opsForList().rightPushAll(key, values);
    }

    @Override
    public Long rightPushIfPresent(K key, V value) {
        return valueOperations.getOperations().opsForList().rightPushIfPresent(key, value);
    }

    @Override
    public Long rightPush(K key, V pivot, V value) {
        return valueOperations.getOperations().opsForList().rightPush(key, value);
    }

    @Override
    public void set(K key, long index, V value) {
        valueOperations.getOperations().opsForList().set(key, index, value);

    }

    @Override
    public Long remove(K key, long count, Object value) {
        return valueOperations.getOperations().opsForList().remove(key, count, value);
    }

    @Override
    public V index(K key, long index) {
        return valueOperations.getOperations().opsForList().index(key, index);
    }

    @Override
    public V leftPop(K key) {
        return valueOperations.getOperations().opsForList().leftPop(key);
    }

    @Override
    public V leftPop(K key, long timeout, TimeUnit unit) {
        return valueOperations.getOperations().opsForList().leftPop(key, timeout, unit);
    }

    @Override
    public V rightPop(K key) {
        return valueOperations.getOperations().opsForList().rightPop(key);
    }

    @Override
    public V rightPop(K key, long timeout, TimeUnit unit) {
        return valueOperations.getOperations().opsForList().rightPop(key, timeout, unit);
    }

    @Override
    public V rightPopAndLeftPush(K sourceKey, K destinationKey) {
        return valueOperations.getOperations().opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
    }

    @Override
    public V rightPopAndLeftPush(K sourceKey, K destinationKey, long timeout, TimeUnit unit) {
        return valueOperations.getOperations().opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout,
                unit);
    }

    @Override
    public RedisOperations<K, V> getOperations() {
        return valueOperations.getOperations();
    }
}
