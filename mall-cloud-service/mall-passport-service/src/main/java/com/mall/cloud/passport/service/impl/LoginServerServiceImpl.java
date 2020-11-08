package com.mall.cloud.passport.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.JsonServerUtil;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.entity.user.CustomerUser;
import com.mall.cloud.model.entity.user.EmployeeUser;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.model.mapper.user.CustomerUserMapper;
import com.mall.cloud.model.mapper.user.EmployeeUserMapper;
import com.mall.cloud.passport.api.service.LoginServerService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>封装Qicloud项目LoginServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 00:16
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Service(version = Constants.DUBBO_SERVICE_VERSION, timeout = Constants.DUBBO_TIMEOUT)
@Component
public class LoginServerServiceImpl extends BaseServerService implements LoginServerService {
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private CustomerUserMapper customerUserMapper;
    @Resource
    private EmployeeUserMapper employeeUserMapper;
    @CreateCache(name = "mall:cloud:adminUser",expire = 100,cacheType =CacheType.REMOTE)
    private Cache<String ,AdminUser> adminUserCache;

    private static  final String cacheUser="mall:cloud:adminUser";

    /**
     * 系统用户登录-根据用户帐号和密码查找用户
     *
     * @param account  系统用户账户
     * @param password 系统用户密码
     * @return 返回结果
     */
    @Override
    public AdminUser queryAdminUser(String account, String password) {
        AdminUser adminUser =adminUserCache.get(cacheUser);
       if (CheckEmptyUtil.isEmpty(adminUser)){
           adminUser=adminUserMapper.selectOne(new QueryWrapper<AdminUser>().eq("account", account).eq("password", password));
           adminUserCache.put(cacheUser, adminUser);
       }
        return adminUser;
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
        return customerUserMapper.selectOne(new QueryWrapper<CustomerUser>().eq("account", account).eq("password", password));
    }

    /**
     * 客户用户登录-根据用户帐号和密码查找用户
     *
     * @param account  客户用户账户
     * @param password 客户用户密码
     * @return 返回结果
     */
    @Override
    public EmployeeUser queryEmployeeUser(String account, String password) {
        return employeeUserMapper.selectOne(new QueryWrapper<EmployeeUser>().eq("account", account).eq("password", password));

    }
}
