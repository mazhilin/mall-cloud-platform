package com.mall.cloud.passport.web.configuration;


import com.mall.cloud.common.component.interceptor.GolbalApplictaionInterceptor;
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
    private GolbalApplictaionInterceptor applictaionInterceptor = new GolbalApplictaionInterceptor();
    private PassportWebInterceptor passportWebInterceptor = new PassportWebInterceptor();

    @Bean
    PassportWebInterceptor webInterceptor() {
        return passportWebInterceptor;
    }

    @Bean
    GolbalApplictaionInterceptor applictaionInterceptor() {
        return applictaionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要拦截的路径
        String[] addPathPatterns = {
                "/**"
        };
        //不需要拦截的路径
        String[] excludePathPatterns = {
                "/api/console/login",
                "/api/console/home/toLogin"
        };
        //addPathPatterns()添加拦截路径
        //excludePathPatterns() 添加不拦截的路径
        //拦截所有请求接口
        registry.addInterceptor(applictaionInterceptor).addPathPatterns(addPathPatterns);
        //添加注册登录拦截器
        registry.addInterceptor(passportWebInterceptor).excludePathPatterns(excludePathPatterns);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600L);
    }
}
