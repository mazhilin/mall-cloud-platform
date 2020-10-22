package com.mall.cloud.console.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>封装Qicloud项目ConsoleWebApplication类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 00:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@SpringBootApplication
public class ConsoleWebApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(ConsoleWebApplication.class, args);
		applicationContext.registerShutdownHook();
		applicationContext.start();
	}
}
