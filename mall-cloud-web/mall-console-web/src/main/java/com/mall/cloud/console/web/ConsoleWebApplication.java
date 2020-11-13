package com.mall.cloud.console.web;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.mall.cloud.common.annotation.container.ApplicationClientBootstrap;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>封装Qicloud项目ConsoleWebApplication类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 00:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@ApplicationClientBootstrap
@EnableDubbo
@EnableMethodCache(basePackages = "com.mall.cloud")
@EnableCreateCacheAnnotation
public class ConsoleWebApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(ConsoleWebApplication.class, args);
		applicationContext.registerShutdownHook();
		applicationContext.start();
	}
}
