package com.mall.cloud.passport.service.impl;

import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Closeable;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目RedisOperationsServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 01:57
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class RedisOperationsServiceImpl<K, V> implements RedisOperationsService<K, V> {
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<K, V> valueOperations;

    @Override
    public <T> T execute(RedisCallback<T> action) {
        return valueOperations.getOperations().execute(action);
    }

    @Override
    public <T> T execute(SessionCallback<T> session) {
        return valueOperations.getOperations().execute(session);
    }

    @Override
    public List<Object> executePipelined(RedisCallback<?> action) {
        return valueOperations.getOperations().executePipelined(action);
    }

    @Override
    public List<Object> executePipelined(RedisCallback<?> action, RedisSerializer<?> resultSerializer) {
        return valueOperations.getOperations().executePipelined(action, resultSerializer);
    }

    @Override
    public List<Object> executePipelined(SessionCallback<?> session) {
        return valueOperations.getOperations().executePipelined(session);
    }

    @Override
    public List<Object> executePipelined(SessionCallback<?> session, RedisSerializer<?> resultSerializer) {
        return valueOperations.getOperations().executePipelined(session, resultSerializer);
    }

    @Override
    public <T> T execute(RedisScript<T> script, List<K> keys, Object... args) {
        return valueOperations.getOperations().execute(script, keys, args);
    }

    @Override
    public <T> T execute(RedisScript<T> script, RedisSerializer<?> argsSerializer, RedisSerializer<T> resultSerializer,
                         List<K> keys, Object... args) {
        return valueOperations.getOperations().execute(script, argsSerializer, resultSerializer, keys, args);
    }

    @Override
    public <T extends Closeable> T executeWithStickyConnection(RedisCallback<T> callback) {
        return valueOperations.getOperations().executeWithStickyConnection(callback);
    }

    @Override
    public Boolean hasKey(K key) {
        return valueOperations.getOperations().hasKey(key);
    }

    @Override
    public Boolean delete(K arg0) {
        return valueOperations.getOperations().delete(arg0);
    }

    @Override
    public Long delete(Collection<K> arg0) {
        return valueOperations.getOperations().delete(arg0);
    }

    @Override
    public DataType type(K key) {
        return valueOperations.getOperations().type(key);
    }

    @Override
    public Set<K> keys(K pattern) {
        return valueOperations.getOperations().keys(pattern);
    }

    @Override
    public K randomKey() {
        return valueOperations.getOperations().randomKey();
    }

    @Override
    public void rename(K oldKey, K newKey) {
        valueOperations.getOperations().rename(oldKey, newKey);
    }

    @Override
    public Boolean renameIfAbsent(K oldKey, K newKey) {
        return valueOperations.getOperations().renameIfAbsent(oldKey, newKey);
    }

    @Override
    public Boolean expire(K key, long timeout, TimeUnit unit) {
        return valueOperations.getOperations().expire(key, timeout, unit);
    }

    @Override
    public Boolean expire(K key, Duration timeout) {
        return null;
    }

    @Override
    public Boolean expireAt(K key, Date date) {
        return valueOperations.getOperations().expireAt(key, date);
    }

    @Override
    public Boolean expireAt(K key, Instant expireAt) {
        return null;
    }

    @Override
    public Boolean persist(K key) {
        return valueOperations.getOperations().persist(key);
    }

    @Override
    public Boolean move(K key, int dbIndex) {
        return valueOperations.getOperations().move(key, dbIndex);
    }

    @Override
    public byte[] dump(K key) {
        return valueOperations.getOperations().dump(key);
    }

    @Override
    public void restore(K key, byte[] value, long timeToLive, TimeUnit unit) {
        valueOperations.getOperations().restore(key, value, timeToLive, unit);
    }

    @Override
    public Long getExpire(K key) {
        return valueOperations.getOperations().getExpire(key);
    }

    @Override
    public Long getExpire(K key, TimeUnit timeUnit) {
        return valueOperations.getOperations().getExpire(key, timeUnit);
    }

    @Override
    public List<V> sort(SortQuery<K> query) {
        return valueOperations.getOperations().sort(query);
    }

    @Override
    public <T> List<T> sort(SortQuery<K> query, RedisSerializer<T> resultSerializer) {
        return valueOperations.getOperations().sort(query, resultSerializer);
    }

    @Override
    public <T> List<T> sort(SortQuery<K> query, BulkMapper<T, V> bulkMapper) {
        return valueOperations.getOperations().sort(query, bulkMapper);
    }

    @Override
    public <T, S> List<T> sort(SortQuery<K> query, BulkMapper<T, S> bulkMapper, RedisSerializer<S> resultSerializer) {
        return valueOperations.getOperations().sort(query, bulkMapper, resultSerializer);
    }

    @Override
    public Long sort(SortQuery<K> query, K storeKey) {
        return valueOperations.getOperations().sort(query, storeKey);
    }

    @Override
    public void watch(K key) {
        valueOperations.getOperations().watch(key);
    }

    @Override
    public void watch(Collection<K> keys) {
        valueOperations.getOperations().watch(keys);
    }

    @Override
    public void unwatch() {
        valueOperations.getOperations().unwatch();
    }

    @Override
    public void multi() {
        valueOperations.getOperations().multi();
    }

    @Override
    public void discard() {
        valueOperations.getOperations().discard();
    }

    @Override
    public List<Object> exec() {
        return valueOperations.getOperations().exec();
    }

    @Override
    public List<Object> exec(RedisSerializer<?> valueSerializer) {
        return valueOperations.getOperations().exec(valueSerializer);
    }

    @Override
    public List<RedisClientInfo> getClientList() {
        return valueOperations.getOperations().getClientList();
    }

    @Override
    public void killClient(String host, int port) {
        valueOperations.getOperations().killClient(host, port);
    }

    @Override
    public void slaveOf(String host, int port) {
        valueOperations.getOperations().slaveOf(host, port);
    }

    @Override
    public void slaveOfNoOne() {
        valueOperations.getOperations().slaveOfNoOne();
    }

    @Override
    public void convertAndSend(String destination, Object message) {
        valueOperations.getOperations().convertAndSend(destination, message);
    }

    @Override
    public ValueOperations<K, V> opsForValue() {
        return valueOperations.getOperations().opsForValue();
    }

    @Override
    public BoundValueOperations<K, V> boundValueOps(K key) {
        return valueOperations.getOperations().boundValueOps(key);
    }

    @Override
    public ListOperations<K, V> opsForList() {
        return valueOperations.getOperations().opsForList();
    }

    @Override
    public BoundListOperations<K, V> boundListOps(K key) {
        return valueOperations.getOperations().boundListOps(key);
    }

    @Override
    public SetOperations<K, V> opsForSet() {
        return valueOperations.getOperations().opsForSet();
    }

    @Override
    public BoundSetOperations<K, V> boundSetOps(K key) {
        return valueOperations.getOperations().boundSetOps(key);
    }

    @Override
    public <HK, HV> StreamOperations<K, HK, HV> opsForStream() {
        return valueOperations.getOperations().opsForStream();
    }

    @Override
    public <HK, HV> StreamOperations<K, HK, HV> opsForStream(HashMapper<? super K, ? super HK, ? super HV> hashMapper) {
        return valueOperations.getOperations().opsForStream(hashMapper);
    }

    @Override
    public <HK, HV> BoundStreamOperations<K, HK, HV> boundStreamOps(K k) {
        return valueOperations.getOperations().boundStreamOps(k);
    }

    @Override
    public ZSetOperations<K, V> opsForZSet() {
        return valueOperations.getOperations().opsForZSet();
    }

    @Override
    public HyperLogLogOperations<K, V> opsForHyperLogLog() {
        return valueOperations.getOperations().opsForHyperLogLog();
    }

    @Override
    public BoundZSetOperations<K, V> boundZSetOps(K key) {
        return valueOperations.getOperations().boundZSetOps(key);
    }

    @Override
    public <HK, HV> HashOperations<K, HK, HV> opsForHash() {
        return valueOperations.getOperations().opsForHash();
    }

    @Override
    public <HK, HV> BoundHashOperations<K, HK, HV> boundHashOps(K key) {
        return valueOperations.getOperations().boundHashOps(key);
    }

    @Override
    public GeoOperations<K, V> opsForGeo() {
        return valueOperations.getOperations().opsForGeo();
    }

    @Override
    public BoundGeoOperations<K, V> boundGeoOps(K key) {
        return valueOperations.getOperations().boundGeoOps(key);
    }

    @Override
    public ClusterOperations<K, V> opsForCluster() {
        return valueOperations.getOperations().opsForCluster();
    }

    @Override
    public RedisSerializer<?> getKeySerializer() {
        return valueOperations.getOperations().getHashKeySerializer();
    }

    @Override
    public RedisSerializer<?> getValueSerializer() {
        return valueOperations.getOperations().getValueSerializer();
    }

    @Override
    public RedisSerializer<?> getHashKeySerializer() {
        return valueOperations.getOperations().getHashKeySerializer();
    }

    @Override
    public RedisSerializer<?> getHashValueSerializer() {
        return valueOperations.getOperations().getHashValueSerializer();
    }

    @Override
    public Long countExistingKeys(Collection<K> arg0) {
        return valueOperations.getOperations().countExistingKeys(arg0);
    }

    @Override
    public void restore(K arg0, byte[] arg1, long arg2, TimeUnit arg3, boolean arg4) {
        valueOperations.getOperations().restore(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public Boolean unlink(K arg0) {
        return valueOperations.getOperations().unlink(arg0);
    }

    @Override
    public Long unlink(Collection<K> arg0) {
        return valueOperations.getOperations().unlink(arg0);
    }
}
