package com.mall.cloud.schedule.web;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.mall.cloud.common.annotation.container.ApplicationClientBootstrap;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * <p>封装Qicloud项目ScheduleWebApplication类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2021-01-08 23:50
 * @version 1.0.0
 * <p>Copyright © 2018-2021 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@ApplicationClientBootstrap
@EnableDubbo
@EnableMethodCache(basePackages = "com.mall.cloud")
@EnableCreateCacheAnnotation
public class ScheduleWebApplication {
}
