package com.mall.cloud.passport.api.service;


import org.springframework.data.redis.core.HashOperations;

/**
 * <p>封装Qicloud项目HashOperationsService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 01:36
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface HashOperationsService<H, HK, HV>  extends HashOperations<H, HK, HV> {
}
