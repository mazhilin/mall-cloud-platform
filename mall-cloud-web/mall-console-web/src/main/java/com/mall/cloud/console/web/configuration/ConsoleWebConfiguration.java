package com.mall.cloud.console.web.configuration;

import com.mall.cloud.common.component.interceptor.GolbalApplictaionInterceptor;
import com.mall.cloud.console.web.ConsoleWebApplication;
import com.mall.cloud.console.web.interceptor.ConsoleWebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>封装Qicloud项目ConsoleWebConfiguration类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-08 15:27
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = ConsoleWebApplication.class, useDefaultFilters = true)
public class ConsoleWebConfiguration implements WebMvcConfigurer {

    private GolbalApplictaionInterceptor applictaionInterceptor = new GolbalApplictaionInterceptor();
    private ConsoleWebInterceptor consoleWebInterceptor = new ConsoleWebInterceptor();


    @Bean
    ConsoleWebInterceptor webInterceptor() {
        return consoleWebInterceptor;
    }

    @Bean
    GolbalApplictaionInterceptor applictaionInterceptor() {
        return applictaionInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要拦截的路径
        String[] addPathPatterns = {"/**"};
        //不需要拦截的路径
        String[] excludePathPatterns = {
                "/api/console/home/toLogin",
                "/main/loginView",
                "/shaker/file/upload",
                "/job/api",
                "/shakerLiveSocket",
                "/documentManager/updateDocumentBlue",
                "/documentManager/updateDocumentSea"
        };
        registry.addInterceptor(applictaionInterceptor).addPathPatterns(addPathPatterns);
        registry.addInterceptor(consoleWebInterceptor).excludePathPatterns(excludePathPatterns);
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
