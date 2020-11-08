package com.mall.cloud.common.configuration;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>封装Qicloud项目EnableAutoContainerConfiguration类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 22:17
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Configuration
@ServletComponentScan
@AutoConfigurationPackage
@Import(value = {GlobalContainerConfiguration.class})
public class EnableAutoContainerConfiguration {
}
