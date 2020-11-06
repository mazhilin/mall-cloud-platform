package com.mall.cloud.passport.api.service;

import org.springframework.data.redis.core.RedisOperations;

/**
 * <p>封装Qicloud项目RedisOperationsService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 01:48
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface RedisOperationsService <K, V> extends RedisOperations<K, V> {
}
