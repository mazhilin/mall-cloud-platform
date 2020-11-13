package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.MD5Util;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.entity.user.CustomerUser;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.model.mapper.user.CustomerUserMapper;
import com.mall.cloud.passport.api.service.LoginServerService;

import javax.annotation.Resource;
import java.util.Map;

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
        query.lambda().eq(AdminUser::getPassword, MD5Util.encodeByMD5(MD5Util.PUBLIC_SALT + password).toLowerCase());
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

    /**
     * 系统后台登录-根据用户帐号和密码查找用户
     *
     * @param response
     * @param account  系统用户账户
     * @param password 系统用户密码
     * @return 返回结果
     */
    @Override
    public ResponseResult login(ResponseResult response, String account, String password) throws ApplicationServerException {
        // [1].校验用户名和密码是否正确
        AdminUser adminUser = this.queryAdminUser(account, MD5Util.encodeByMD5(MD5Util.PUBLIC_SALT+password).toLowerCase());
        if (CheckEmptyUtil.isEmpty(adminUser)) {
            response.setError("用户不存在!");
            return response;
        }

        Map<String,Object> resultMap = Maps.newConcurrentMap();
        resultMap.put("adminUser",adminUser);
        response.setResult(resultMap);
        return response;
    }
}
