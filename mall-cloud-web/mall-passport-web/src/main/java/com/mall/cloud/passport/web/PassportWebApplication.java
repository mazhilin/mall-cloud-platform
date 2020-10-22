package com.mall.cloud.passport.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>封装Qicloud项目PassportWebApplication类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 01:09
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@SpringBootApplication
public class PassportWebApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(PassportWebApplication.class, args);
		applicationContext.registerShutdownHook();
		applicationContext.start();
	}
}
