package com.mall.cloud.passport.api.service;

import org.springframework.data.redis.core.SetOperations;

/**
 * <p>封装Qicloud项目SetOperationsService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 01:48
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface SetOperationsService<K, V> extends SetOperations<K, V> {
}
