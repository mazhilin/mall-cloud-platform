package com.mall.cloud.passport.web.controller.common;

import com.mall.cloud.common.annotation.ApplicationAuthorize;
import com.mall.cloud.common.constant.PlatformType;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.restful.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>封装Qicloud项目SmallCentterController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 14:38
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping(value = "/api/small/center")
public class SmallCenterController extends BaseController {
    /**
     * 原生登录方法-nativeLogin
     *
     * @param account    登录账户[系统用户,APP用户,员工用户]
     * @param password   登录密码
     * @param clientType 客户端类型[app-手机端APP登录,web-PC客户端登录]
     * @param userType   用户类型[0-系统用户,1-APP用户,2-员工]
     * @return 返回结果
     */
    @PostMapping(value = "/login")
    @ApplicationAuthorize( authorizeScope = ScopeType.SMR)
    public ResponseResult login(
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "clientType", required = false, defaultValue = "smr") String clientType,
            @RequestParam(value = "userType", required = false, defaultValue = "0") Integer userType) throws Exception {

        return new ResponseResult();
    }
}
