package com.mall.cloud.passport.web;

import com.mall.cloud.common.container.annotation.ApplicationClientBootstrap;
import org.springframework.boot.SpringApplication;

/**
 * <p>封装Qicloud项目PassportWebApplication类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 01:09
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@ApplicationClientBootstrap
public class PassportWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(PassportWebApplication.class, args);
	}
}
