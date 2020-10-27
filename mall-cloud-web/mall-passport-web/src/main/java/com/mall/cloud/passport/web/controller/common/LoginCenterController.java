package com.mall.cloud.passport.web.controller.common;

import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.restful.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>封装Qicloud项目PassportLoginController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-27 14:54
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping(value = "/api/login")
public class LoginCenterController extends BaseController {

	/**
	 * 原生登录方法-nativeLogin
	 *
	 * @param account    登录账户[系统用户,APP用户,员工用户]
	 * @param password   登录密码
	 * @param clientType 客户端类型[app-手机端APP登录,web-PC客户端登录]
	 * @param userType   用户类型[0-系统用户,1-APP用户,2-员工]
	 * @return 返回结果
	 */
	@PostMapping(value = "/native")
	public ResponseResult nativeLogin(
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "clientType", required = false) String clientType,
			@RequestParam(value = "userType", required = false) Integer userType) throws Exception {

		return new ResponseResult();
	}


	/**
	 * 第三方登录方法-oauth
	 *
	 * @param account  登陆账号
	 * @param password 登陆密码
	 * @return 结果
	 */
	@PostMapping(value = "/oauth", produces = "application/json;charset=UTF-8")
	public ResponseResult oauthLogin(
			@RequestParam(value = "account") String account,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "clientType", required = false) String clientType,
			@RequestParam(value = "userType", required = false) Integer userType) {
		return new ResponseResult();
	}
}
