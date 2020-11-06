package com.mall.cloud.passport.service.impl;

import com.mall.cloud.passport.api.service.ZSetOperationsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * <p>封装Qicloud项目ZSetOperationsServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 02:15
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Component(value = "ZSetOperationsServiceImpl")
@Service(interfaceClass = ZSetOperationsService.class)
public class ZSetOperationsServiceImpl <K, V> implements ZSetOperationsService<K, V> {
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<K, V> valueOperations;

    @Override
    public Boolean add(K key, V value, double score) {
        return valueOperations.getOperations().opsForZSet().add(key, value, score);
    }

    @Override
    public Long add(K key, Set<TypedTuple<V>> tuples) {
        return valueOperations.getOperations().opsForZSet().add(key, tuples);
    }

    @Override
    public Long remove(K key, Object... values) {
        return valueOperations.getOperations().opsForZSet().remove(key, values);
    }

    @Override
    public Double incrementScore(K key, V value, double delta) {
        return valueOperations.getOperations().opsForZSet().incrementScore(key, value, delta);
    }

    @Override
    public Long rank(K key, Object o) {
        return valueOperations.getOperations().opsForZSet().rank(key, o);
    }

    @Override
    public Long reverseRank(K key, Object o) {
        return valueOperations.getOperations().opsForZSet().reverseRank(key, o);
    }

    @Override
    public Set<V> range(K key, long start, long end) {
        return valueOperations.getOperations().opsForZSet().range(key, start, end);
    }

    @Override
    public Set<TypedTuple<V>> rangeWithScores(K key, long start, long end) {
        return valueOperations.getOperations().opsForZSet().rangeWithScores(key, start, end);
    }

    @Override
    public Set<V> rangeByScore(K key, double min, double max) {
        return valueOperations.getOperations().opsForZSet().rangeByScore(key, min, max);
    }

    @Override
    public Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max) {
        return valueOperations.getOperations().opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<V> rangeByScore(K key, double min, double max, long offset, long count) {
        return valueOperations.getOperations().opsForZSet().rangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max, long offset, long count) {
        return valueOperations.getOperations().opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<V> reverseRange(K key, long start, long end) {
        return valueOperations.getOperations().opsForZSet().reverseRange(key, start, end);
    }

    @Override
    public Set<TypedTuple<V>> reverseRangeWithScores(K key, long start, long end) {
        return valueOperations.getOperations().opsForZSet().reverseRangeWithScores(key, start, end);
    }

    @Override
    public Set<V> reverseRangeByScore(K key, double min, double max) {
        return valueOperations.getOperations().opsForZSet().reverseRangeByScore(key, min, max);
    }

    @Override
    public Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max) {
        return valueOperations.getOperations().opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<V> reverseRangeByScore(K key, double min, double max, long offset, long count) {
        return valueOperations.getOperations().opsForZSet().reverseRangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max, long offset, long count) {
        return valueOperations.getOperations().opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Long count(K key, double min, double max) {
        return valueOperations.getOperations().opsForZSet().count(key, min, max);
    }

    @Override
    public Long size(K key) {
        return valueOperations.getOperations().opsForZSet().size(key);
    }

    @Override
    public Long zCard(K key) {
        return valueOperations.getOperations().opsForZSet().zCard(key);
    }

    @Override
    public Double score(K key, Object o) {
        return valueOperations.getOperations().opsForZSet().score(key, o);
    }

    @Override
    public Long removeRange(K key, long start, long end) {
        return valueOperations.getOperations().opsForZSet().removeRange(key, start, end);
    }

    @Override
    public Long removeRangeByScore(K key, double min, double max) {
        return valueOperations.getOperations().opsForZSet().removeRangeByScore(key, min, max);
    }

    @Override
    public Long unionAndStore(K key, K otherKey, K destKey) {
        return valueOperations.getOperations().opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    @Override
    public Long unionAndStore(K key, Collection<K> otherKeys, K destKey) {
        return valueOperations.getOperations().opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Long intersectAndStore(K key, K otherKey, K destKey) {
        return valueOperations.getOperations().opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    @Override
    public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey) {
        return valueOperations.getOperations().opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    @Override
    public Cursor<TypedTuple<V>> scan(K key, ScanOptions options) {
        return valueOperations.getOperations().opsForZSet().scan(key, options);
    }

    @Override
    public Set<V> rangeByLex(K key, RedisZSetCommands.Range range) {
        return valueOperations.getOperations().opsForZSet().rangeByLex(key, range);
    }

    @Override
    public Set<V> rangeByLex(K key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit) {
        return valueOperations.getOperations().opsForZSet().rangeByLex(key, range, limit);
    }

    @Override
    public RedisOperations<K, V> getOperations() {
        return valueOperations.getOperations();
    }

    @Override
    public Long intersectAndStore(K arg0, Collection<K> arg1, K arg2, RedisZSetCommands.Aggregate arg3, RedisZSetCommands.Weights arg4) {
        return valueOperations.getOperations().opsForZSet().intersectAndStore(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public Long unionAndStore(K arg0, Collection<K> arg1, K arg2, RedisZSetCommands.Aggregate arg3, RedisZSetCommands.Weights arg4) {
        return valueOperations.getOperations().opsForZSet().unionAndStore(arg0, arg1, arg2, arg3, arg4);
    }
}
