package com.mall.cloud.passport.service.impl;

import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.passport.api.service.SetOperationsService;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>封装Qicloud项目SetOperationsServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 02:02
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class SetOperationsServiceImpl<K, V> implements SetOperationsService<K, V> {
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<K, V> valueOperations;

    @Override
    public RedisOperations<K, V> getOperations() {
        return valueOperations.getOperations();
    }

    @Override
    public Long add(K key, V... values) {
        return valueOperations.getOperations().opsForSet().add(key, values);
    }

    @Override
    public Long remove(K key, Object... values) {
        return valueOperations.getOperations().opsForSet().remove(key, values);
    }

    @Override
    public V pop(K key) {
        return valueOperations.getOperations().opsForSet().pop(key);
    }

    @Override
    public List<V> pop(K key, long count) {
        return valueOperations.getOperations().opsForSet().pop(key, count);
    }

    @Override
    public Boolean move(K key, V value, K destKey) {
        return valueOperations.getOperations().opsForSet().move(key, value, destKey);
    }

    @Override
    public Long size(K key) {
        return valueOperations.getOperations().opsForSet().size(key);
    }

    @Override
    public Boolean isMember(K key, Object o) {
        return valueOperations.getOperations().opsForSet().isMember(key, o);
    }

    @Override
    public Set<V> intersect(K key, K otherKey) {
        return valueOperations.getOperations().opsForSet().intersect(key, otherKey);
    }

    @Override
    public Set<V> intersect(K key, Collection<K> otherKeys) {
        return valueOperations.getOperations().opsForSet().intersect(key, otherKeys);
    }

    @Override
    public Long intersectAndStore(K key, K otherKey, K destKey) {
        return valueOperations.getOperations().opsForSet().intersectAndStore(key, otherKey, destKey);
    }

    @Override
    public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey) {
        return valueOperations.getOperations().opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<V> union(K key, K otherKey) {
        return valueOperations.getOperations().opsForSet().union(key, otherKey);
    }

    @Override
    public Set<V> union(K key, Collection<K> otherKeys) {
        return valueOperations.getOperations().opsForSet().union(key, otherKeys);
    }

    @Override
    public Long unionAndStore(K key, K otherKey, K destKey) {
        return valueOperations.getOperations().opsForSet().unionAndStore(key, otherKey, destKey);
    }

    @Override
    public Long unionAndStore(K key, Collection<K> otherKeys, K destKey) {
        return valueOperations.getOperations().opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<V> difference(K key, K otherKey) {
        return valueOperations.getOperations().opsForSet().difference(key, otherKey);
    }

    @Override
    public Set<V> difference(K key, Collection<K> otherKeys) {
        return valueOperations.getOperations().opsForSet().difference(key, otherKeys);
    }

    @Override
    public Long differenceAndStore(K key, K otherKey, K destKey) {
        return valueOperations.getOperations().opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    @Override
    public Long differenceAndStore(K key, Collection<K> otherKeys, K destKey) {
        return valueOperations.getOperations().opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<V> members(K key) {
        return valueOperations.getOperations().opsForSet().members(key);
    }

    @Override
    public V randomMember(K key) {
        return valueOperations.getOperations().opsForSet().randomMember(key);
    }

    @Override
    public Set<V> distinctRandomMembers(K key, long count) {
        return valueOperations.getOperations().opsForSet().distinctRandomMembers(key, count);
    }

    @Override
    public List<V> randomMembers(K key, long count) {
        return valueOperations.getOperations().opsForSet().randomMembers(key, count);
    }

    @Override
    public Cursor<V> scan(K key, ScanOptions options) {
        return valueOperations.getOperations().opsForSet().scan(key, options);
    }

    @Override
    public Set<V> intersect(Collection<K> collection) {
        return valueOperations.getOperations().opsForSet().intersect(collection);
    }

    @Override
    public Long intersectAndStore(Collection<K> collection, K k) {
        return valueOperations.getOperations().opsForSet().intersectAndStore(collection, k);
    }

    @Override
    public Set<V> union(Collection<K> collection) {
        return valueOperations.getOperations().opsForSet().union(collection);
    }

    @Override
    public Long unionAndStore(Collection<K> collection, K k) {
        return valueOperations.getOperations().opsForSet().unionAndStore(collection, k);
    }

    @Override
    public Set<V> difference(Collection<K> collection) {
        return valueOperations.getOperations().opsForSet().difference(collection);
    }

    @Override
    public Long differenceAndStore(Collection<K> collection, K k) {
        return valueOperations.getOperations().opsForSet().differenceAndStore(collection, k);
    }
}
