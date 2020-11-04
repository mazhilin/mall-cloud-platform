package com.mall.cloud.monitor.api.service;

import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.monitor.api.param.ApplicationLoggerParam;

/**
 * <p>封装Qicloud项目JournalServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 09:10
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface JournalServerService extends BaseService {


    /**
     * 创建系统操作日志
     *
     * @param param 操作日志对象
     */
    void save(ApplicationLoggerParam param);

}
