package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.entity.user.CustomerUser;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.model.mapper.user.CustomerUserMapper;
import com.mall.cloud.passport.api.service.LoginServerService;

import javax.annotation.Resource;

/**
 * <p>封装Qicloud项目LoginServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 00:16
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class LoginServerServiceImpl extends BaseServerService implements LoginServerService {
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private CustomerUserMapper customerUserMapper;
    /**
     * 系统用户登录-根据用户帐号和密码查找用户
     *
     * @param account  系统用户账户
     * @param password 系统用户密码
     * @return 返回结果
     */
    @Override
    public AdminUser queryAdminUser(String account, String password) {
        QueryWrapper<AdminUser> query = new QueryWrapper<>();
        query.lambda().eq(AdminUser::getAccount, account);
        query.lambda().eq(AdminUser::getPassword, password);
        query.lambda().eq(AdminUser::getStatus, 1);
        return adminUserMapper.selectOne(query);
    }

    /**
     * 客户用户登录-根据用户帐号和密码查找用户
     *
     * @param account  客户用户账户
     * @param password 客户用户密码
     * @return 返回结果
     */
    @Override
    public CustomerUser queryCustomerUser(String account, String password) {
        QueryWrapper<CustomerUser> query = new QueryWrapper<>();
        query.lambda().eq(CustomerUser::getAccount, account);
        query.lambda().eq(CustomerUser::getPassword, password);
        query.lambda().eq(CustomerUser::getStatus, 1);
        return customerUserMapper.selectOne(query);
    }
}
