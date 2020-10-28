package com.mall.cloud.mobile.service;

import com.mall.cloud.common.container.annotation.ApplicationServerBootstrap;
import org.springframework.boot.SpringApplication;

/**
 * <p>封装Qicloud项目MobileServiceApplication类.<br></p>
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 00:54
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@ApplicationServerBootstrap
public class MobileServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MobileServiceApplication.class, args);
	}
}
