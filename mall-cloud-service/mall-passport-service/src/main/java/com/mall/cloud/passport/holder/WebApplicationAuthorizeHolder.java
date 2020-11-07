package com.mall.cloud.passport.holder;

import com.mall.cloud.common.constant.PlatformType;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.passport.api.authorize.WebApplicationAuthorize;
import com.mall.cloud.passport.api.service.ListOperationsService;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.SetOperationsService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * <p>封装Qicloud项目WebAuthorizeHolder类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 15:25
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Component
@Service(interfaceClass = WebApplicationAuthorize.class)
public class WebApplicationAuthorizeHolder extends ApplicationAuthorizeHolder implements WebApplicationAuthorize {
    @Reference
    protected ValueOperationsService<String, String> valueOperationsService;
    @Reference
    protected RedisOperationsService<String, String> redisOperationsService;
    @Reference
    protected ListOperationsService<String, String> listOperationsService;
    @Reference
    protected SetOperationsService<String, String> setOperationsService;

    /**
     * @return String
     */
    @Override
    public String getTokenKey() {
        return null;
    }

    /**
     * 登录会话有效时长
     *
     * @return
     */
    @Override
    public Long getSessionTimeOut() {
        return null;
    }

    /**
     * 获取登录鉴权CookieName
     *
     * @return
     */
    @Override
    public String getAuthorizeCookieName() {
        return null;
    }

    /**
     * 获取端类型
     *
     * @return
     */
    @Override
    public ScopeType getScope() {
        return ScopeType.CONSOLE;
    }

    @Override
    public PlatformType getPlatform() {
        return PlatformType.WEB;
    }
}
