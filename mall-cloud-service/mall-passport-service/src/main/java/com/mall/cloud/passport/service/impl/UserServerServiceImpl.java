package com.mall.cloud.passport.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Resources;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.mapper.user.AdminUserMapper;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.UserServerService;
import com.mall.cloud.passport.api.service.ValueOperationsService;

import javax.annotation.Resource;
import java.time.Duration;

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
    @DubboConsumerClient
    private RedisOperationsService<String, Object> redisOperationsService;
    @DubboConsumerClient
    private ValueOperationsService<String, Object> valueOperationsService;
    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 验证并刷新token
     *
     * @param token token
     * @return 用户数据
     */
    @Override
    public AdminUser validationAndRefreshToken(String token) {
        // 判断Token是否为空
        if (CheckEmptyUtil.isEmpty(token)) {
            return null;
        }
        AdminUser adminUser = null;
        // 验证Token生命周期
        String tokenKey = Resources.APP_USER_LOGIN_TOKEN + token;
        try {
            //验证reids中有没有
            Object userData = valueOperationsService.get(tokenKey);
            if (ObjectUtil.isNotNull(userData) && StrUtil.isNotBlank(userData.toString())) {
                //重新设置 30 分钟
                int expireSeconds = 60 * 30;
                redisOperationsService.expire(tokenKey, Duration.ofDays(expireSeconds));
                //刷新成功返回用户数据
                JSONObject userJson = JSONObject.parseObject(userData.toString());
                adminUser = JSONObject.toJavaObject(userJson, AdminUser.class);
            }
        } catch (Exception e) {
            logger.error("后台token验证时，更新redis token值失败：{}", e);
            return null;
        }
        return adminUser;
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @Override
    public AdminUser queryUserInfo(String userId) {
        return adminUserMapper.selectById(userId);
    }
}
