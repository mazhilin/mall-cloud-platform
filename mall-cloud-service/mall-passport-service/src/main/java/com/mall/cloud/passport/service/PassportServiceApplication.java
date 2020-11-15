package com.mall.cloud.passport.service;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.mall.cloud.common.annotation.container.ApplicationServerBootstrap;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>封装Qicloud项目PassportServiceApplication类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 00:56
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@ApplicationServerBootstrap
@EnableDubbo(scanBasePackages = {"com.mall.cloud.passport.service.impl"})
@MapperScan(basePackages = {"com.mall.cloud.model.mapper"})
@EnableMethodCache(basePackages = "com.mall.cloud")
@EnableCreateCacheAnnotation
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class PassportServiceApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(PassportServiceApplication.class, args);
		applicationContext.registerShutdownHook();
		applicationContext.start();
	}
}
