package com.mall.cloud.common.component.authorize;

import com.mall.cloud.common.component.BaseApplicationAuthorize;

import java.util.List;

/**
 * <p>封装Qicloud项目BaseLoginAuthorize类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-12 22:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public interface BaseLoginAuthorize {
    String login(String userId, List<String> resourceList, BaseApplicationAuthorize authorize) throws Exception;

    boolean logout(String token, BaseApplicationAuthorize authorize);
}
