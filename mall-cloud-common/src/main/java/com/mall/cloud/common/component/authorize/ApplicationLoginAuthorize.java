package com.mall.cloud.common.component.authorize;

import com.mall.cloud.common.component.BaseApplicationAuthorize;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.utils.EncodeUtil;
import com.mall.cloud.common.utils.TokenServerUtil;
import lombok.SneakyThrows;

import javax.servlet.http.Cookie;
import java.util.List;

/**
 * <p>封装Qicloud项目ApplicationLoginAuthorize类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 22:35
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public abstract class ApplicationLoginAuthorize extends BaseController implements BaseLoginAuthorize {

    /**
     * 登录
     *
     * @param userId       用户id
     * @param resourceList 用户资源列表
     * @param authorize    authorize鉴权客户端类型
     * @return
     * @throws PassportServerException
     */
    @SneakyThrows
    @Override
    public String login(String userId, List<String> resourceList, BaseApplicationAuthorize authorize) throws PassportServerException {
        String token = TokenServerUtil.getInstance().create(null, userId, "login", Constants.PUBLIC_SALT, 30);
        // 记录用户登录状态
        authorize.setAuthorize(userId, token);
        // 先删除以前的权限资源
        authorize.deleteResources(userId);
        // 设置权限资源
        authorize.setResources(resourceList, userId);

        /**
         * 写token cookie到前端
         */
        Cookie cookieToken = new Cookie(authorize.getAuthorizeCookie(), token);
        cookieToken.setMaxAge(-1);
        cookieToken.setPath("/");
        response.addCookie(cookieToken);
        // 写一个静态用户唯一的token到前端，来判断是同一个用户登录
        Cookie userStaticCookieToken = new Cookie(Tokens.USER_STATIC_TOKEN + authorize.getAuthorizeCookie(),
                EncodeUtil.encodeHex(userId.getBytes()));
        userStaticCookieToken.setMaxAge(-1);
        userStaticCookieToken.setPath("/");
        response.addCookie(userStaticCookieToken);
        return token;
    }

    /**
     * 退出登录
     *
     * @param token     token
     * @param authorize authorize鉴权客户端类型
     * @return
     */
    @Override
    public boolean logout(String token, BaseApplicationAuthorize authorize) {
        String userId = authorize.getUserId(token);
        authorize.deleteAuthorize(token);
        authorize.deleteResources(userId);
        authorize.deleteResourceCode(userId);
        return true;
    }
}
