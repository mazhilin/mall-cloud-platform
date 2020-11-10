package com.mall.cloud.common.annotation.dubbo;

import com.mall.cloud.common.constant.Constants;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>封装Qicloud项目DubboProviderServer类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 17:30
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Component
@Service(version = Constants.DUBBO_SERVICE_VERSION, timeout = Constants.DUBBO_TIMEOUT)
public @interface DubboProviderServer {
}
