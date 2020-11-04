package com.mall.cloud.console.service;

import com.mall.cloud.common.container.annotation.ApplicationServerBootstrap;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

/**
 * <p>封装Qicloud项目ConsoleServiceApplication类.<br></p>
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 00:55
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@ApplicationServerBootstrap
@EnableDubbo
@MapperScan(basePackages = {"com.mall.cloud.model.mapper"})
public class ConsoleServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsoleServiceApplication.class, args);
	}
}
