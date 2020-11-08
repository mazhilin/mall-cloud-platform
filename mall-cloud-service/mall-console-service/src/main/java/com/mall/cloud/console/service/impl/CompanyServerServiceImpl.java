package com.mall.cloud.console.service.impl;

import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.console.api.service.CompanyServerService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * <p>封装Qicloud项目CompanyServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-05 08:56
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Service(version = Constants.DUBBO_SERVICE_VERSION, timeout = Constants.DUBBO_TIMEOUT)
@Component
public class CompanyServerServiceImpl extends BaseServerService implements CompanyServerService {
}
