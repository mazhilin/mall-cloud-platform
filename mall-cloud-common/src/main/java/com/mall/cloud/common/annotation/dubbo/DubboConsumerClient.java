package com.mall.cloud.common.annotation.dubbo;

import com.mall.cloud.common.constant.Constants;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>封装Qicloud项目DubboConsumerClient类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 17:31
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
@Reference(
        version = Constants.DUBBO_SERVICE_VERSION,
        timeout = Constants.DUBBO_TIMEOUT,
        check = Constants.DUBBO_CHECK,
        retries = Constants.DUBBO_RETRIES)
public @interface DubboConsumerClient {
}
