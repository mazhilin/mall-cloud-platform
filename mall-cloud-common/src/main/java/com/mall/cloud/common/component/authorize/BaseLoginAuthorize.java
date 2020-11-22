package com.mall.cloud.common.component.authorize;

import com.mall.cloud.common.component.BaseApplicationAuthorize;

import java.util.List;

/**
 * <p>封装Qicloud项目BaseLoginAuthorize类.<br></p>
 * <p>系统全局登录鉴权接口<br></p>
 *
 * @author Powered by marklin 2020-11-12 22:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface BaseLoginAuthorize {
    /**
     * 登录方法
     * @param userId 用户id
     * @param resourceList 用户资源列表
     * @param authorize authorize鉴权客户端类型
     * @return 返回结果
     * @throws Exception 异常消息
     */
    String login(String userId, List<String> resourceList, BaseApplicationAuthorize authorize) throws Exception;

    /**
     * 退出登录
     * @param token token
     * @param authorize  authorize鉴权客户端类型
     * @return
     */
    boolean logout(String token, BaseApplicationAuthorize authorize);
}
