package com.mall.cloud.console.service.impl;


import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.console.api.param.ApplicationLoggerParam;
import com.mall.cloud.console.api.service.JournalServerService;
import com.mall.cloud.model.entity.monitor.JournalInfo;
import com.mall.cloud.model.entity.monitor.JournalItem;
import com.mall.cloud.model.mapper.monitor.JournalInfoMapper;
import com.mall.cloud.model.mapper.monitor.JournalItemMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>封装Qicloud项目JournalServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 09:14
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class JournalServerServiceImpl extends BaseServerService implements JournalServerService {
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
        JournalInfo info = new JournalInfo();
        info.setCreateBy(param.getCreateBy());
        journalInfoMapper.insert(info);
        JournalItem item = new JournalItem();
        item.setJournalId(info.getId());
        item.setUpdateBy(param.getUpdateBy());
        item.setCreateBy(param.getCreateBy());
        journalItemMapper.insert(item);
    }
}
