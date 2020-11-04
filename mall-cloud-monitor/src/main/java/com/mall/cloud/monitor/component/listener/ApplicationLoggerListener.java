package com.mall.cloud.monitor.component.listener;


import com.mall.cloud.console.api.param.ApplicationLoggerParam;
import com.mall.cloud.console.api.service.JournalServerService;
import com.mall.cloud.monitor.component.event.ApplicationLoggerEvent;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <p>封装Qicloud项目ApplicationLoggerListener类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 21:14
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */


@Component
@AllArgsConstructor
public class ApplicationLoggerListener {

    @Reference(version = "1.0.0")
    private JournalServerService journalServerService;




    @Async
    @Order
    @EventListener(ApplicationLoggerEvent.class)
    public void saveSysLog(ApplicationLoggerEvent event) {
        ApplicationLoggerParam applicationLogger = event.getApplicationLogger();
        journalServerService.save(applicationLogger);
    }
}
