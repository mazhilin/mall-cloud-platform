package com.mall.cloud.console.service.impl;


import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.console.api.param.RequestLoggerParam;
import com.mall.cloud.console.api.service.RequestServerService;
import com.mall.cloud.model.entity.monitor.RequestInfo;
import com.mall.cloud.model.mapper.monitor.RequestInfoMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>封装Qicloud项目RequestServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 09:15
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class RequestServerServiceImpl extends BaseServerService implements RequestServerService {

    @Resource
    private RequestInfoMapper requestInfoMapper;
    /**
     * 创建系统操作日志
     *
     * @param param 操作日志对象
     */
    @Override
    public void save(RequestLoggerParam param) {
        RequestInfo request =new RequestInfo();
        request.setRequestUserId(param.getRequestUserId());
        request.setRequestUserType(param.getRequestUserType());
        requestInfoMapper.insert(request);
    }
}
