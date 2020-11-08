package com.mall.cloud.passport.web.configuration;


import com.mall.cloud.passport.web.PassportWebApplication;
import com.mall.cloud.passport.web.interceptor.PassportWebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>封装Qicloud项目PassportWebConfiguration类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-08 15:37
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = PassportWebApplication.class, useDefaultFilters = true)
public class PassportWebConfiguration implements WebMvcConfigurer {

    private PassportWebInterceptor passportWebInterceptor = new PassportWebInterceptor();

    @Bean
    PassportWebInterceptor webInterceptor() {
        return passportWebInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
}
