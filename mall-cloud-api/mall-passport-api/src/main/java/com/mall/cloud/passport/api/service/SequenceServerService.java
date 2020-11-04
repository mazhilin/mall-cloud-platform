package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.persistence.service.BaseService;

/**
 * <p>封装Qicloud项目SequenceServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-30 00:41
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface SequenceServerService extends BaseService {
    /**
     * @return
     * @throws Exception
     */
    String getZookeeperData() throws Exception;

    /**
     * @throws Exception
     */
    void deleteZookeeperData() throws Exception;

    /**
     * 获取分布式全局id
     *
     * @return
     */
    String generateSequenceId();
}
