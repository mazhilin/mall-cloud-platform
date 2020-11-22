package com.mall.cloud.mobile.service;

import com.mall.cloud.common.annotation.container.ApplicationServerBootstrap;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>封装Qicloud项目MobileServiceApplication类.<br></p>
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 00:54
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@ApplicationServerBootstrap
@EnableDubbo
@MapperScan(basePackages = {"com.mall.cloud.model.mapper"})
public class MobileServiceApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(MobileServiceApplication.class, args);
		applicationContext.registerShutdownHook();
		applicationContext.start();
	}
}
