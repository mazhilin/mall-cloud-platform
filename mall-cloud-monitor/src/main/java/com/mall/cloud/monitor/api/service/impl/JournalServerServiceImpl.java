package com.mall.cloud.monitor.api.service.impl;

import com.mall.cloud.model.mapper.monitor.JournalInfoMapper;
import com.mall.cloud.model.mapper.monitor.JournalItemMapper;
import com.mall.cloud.monitor.api.param.ApplicationLoggerParam;
import com.mall.cloud.monitor.api.service.JournalServerService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * <p>封装Qicloud项目JournalServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 09:14
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Service
public class JournalServerServiceImpl implements JournalServerService {
    @Resource
    private JournalInfoMapper journalInfoMapper;

    @Resource
    private JournalItemMapper journalItemMapper;
    /**
     * 创建系统操作日志
     *
     * @param param
     */
    @Override
    public void save(ApplicationLoggerParam param) {

    }
}
