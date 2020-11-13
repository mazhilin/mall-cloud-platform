package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.entity.user.CustomerUser;

/**
 * <p>封装Qicloud项目LoginServerService类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-30 00:03
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface LoginServerService extends BaseService {

    /**
     * 系统用户登录-根据用户帐号和密码查找用户
     *
     * @param account  系统用户账户
     * @param password 系统用户密码
     * @return 返回结果
     */
    AdminUser queryAdminUser(String account, String password);

    /**
     * 客户用户登录-根据用户帐号和密码查找用户
     *
     * @param account  客户用户账户
     * @param password 客户用户密码
     * @return 返回结果
     */
    CustomerUser queryCustomerUser(String account, String password);

    /**
     * 系统后台登录-根据用户帐号和密码查找用户
     *
     * @param account  系统用户账户
     * @param password 系统用户密码
     * @return 返回结果
     */
    ResponseResult login(ResponseResult response,String account, String password) throws ApplicationServerException;
}
