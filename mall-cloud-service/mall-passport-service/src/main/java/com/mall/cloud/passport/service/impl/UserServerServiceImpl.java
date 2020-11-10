package com.mall.cloud.passport.service.impl;


import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.service.UserServerService;

/**
 * <p>封装Qicloud项目UserServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 19:55
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class UserServerServiceImpl extends BaseServerService implements UserServerService {
    /**
     * 验证并刷新token
     *
     * @param token token
     * @return 用户数据
     */
    @Override
    public AdminUser validationAndRefreshToken(String token) {
        return null;
    }
}
