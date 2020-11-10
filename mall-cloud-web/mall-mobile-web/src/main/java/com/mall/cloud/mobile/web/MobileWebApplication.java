package com.mall.cloud.mobile.web;

import com.mall.cloud.common.annotation.container.ApplicationClientBootstrap;
import org.springframework.boot.SpringApplication;

/**
 * <p>封装Qicloud项目MobileWebApplication类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 00:31
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@ApplicationClientBootstrap
public class MobileWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(MobileWebApplication.class, args);
	}
}
