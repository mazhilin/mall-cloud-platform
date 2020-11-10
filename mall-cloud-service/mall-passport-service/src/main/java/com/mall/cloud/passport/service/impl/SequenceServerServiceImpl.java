package com.mall.cloud.passport.service.impl;

import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.utils.SequenceServerUtil;
import com.mall.cloud.model.result.sytem.SequenceIdResult;
import com.mall.cloud.passport.api.service.SequenceServerService;

/**
 * <p>封装Qicloud项目SequenceServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-30 00:42
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class SequenceServerServiceImpl extends BaseServerService implements SequenceServerService {

    private SequenceServerUtil sequenceServer;

    /**
     * @return
     * @throws Exception
     */
    @Override
    public String getZookeeperData() throws Exception {
        return null;
    }

    /**
     * @throws Exception
     */
    @Override
    public void deleteZookeeperData() throws Exception {

    }

    /**
     * 获取分布式全局id
     *
     * @return
     */
    @Override
    public String generateSequenceId() {
        SequenceIdResult result = new SequenceIdResult();
        result.setId(String.valueOf(SequenceServerUtil.getInstance().produceId()));
        if (result != null) {
            //成功返回id
            return result.getId();
        } else {
            logger.error("==========获取分布式全局id====失败=====：" + result.toString());
            //后续应该加消息提示 服务失败了）
            throw new RuntimeException(result.toString());
        }
    }

  public static void main(String[] args) {
    //
      SequenceServerService s=new SequenceServerServiceImpl();
      s.generateSequenceId();
      logger.info(s.generateSequenceId());
  }
}
