package com.mall.cloud.monitor.configuration;

import com.mall.cloud.console.api.service.JournalServerService;
import com.mall.cloud.monitor.component.aspect.ApplicationLoggerAspect;
import com.mall.cloud.monitor.component.listener.ApplicationLoggerListener;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * <p>封装Qicloud项目EnableApplicationLoggerConfiguration类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 22:09
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
@Component
public class EnableApplicationLoggerConfiguration {
    @Reference
    private JournalServerService journalServerService;

    @Bean
    public ApplicationLoggerListener applicationLoggerListener() {
        return new ApplicationLoggerListener(journalServerService);
    }

    @Bean
    public ApplicationLoggerAspect applicationLoggerAspect(ApplicationEventPublisher publisher) {
        return new ApplicationLoggerAspect(publisher);
    }
}
