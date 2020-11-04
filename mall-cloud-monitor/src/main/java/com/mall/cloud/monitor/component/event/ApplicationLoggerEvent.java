package com.mall.cloud.monitor.component.event;

import com.mall.cloud.monitor.api.param.ApplicationLoggerParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>封装Qicloud项目ApplicationLoggerEvent类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 21:12
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Getter
@AllArgsConstructor
public class ApplicationLoggerEvent {

    private final ApplicationLoggerParam applicationLogger;
}
