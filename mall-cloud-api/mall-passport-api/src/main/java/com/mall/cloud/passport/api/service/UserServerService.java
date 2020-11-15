package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.model.entity.user.AdminUser;

/**
 * <p>封装Qicloud项目AdminUserService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-24 00:52
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface UserServerService extends BaseService {

    /**
     * 验证并刷新token
     *
     * @param token token
     * @return 用户数据
     */
    AdminUser validationAndRefreshToken(String token);

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return 返回结果
     */
    AdminUser queryUserInfo(String userId);
}
