package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目ScopeType类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-24 00:34
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public enum ScopeType implements BaseHandler {
	/**后台-0*/
	CONSOLE(0, "后台"),
	/**前台-1*/
	FRONT(1, "前台"),
	/**官网-2*/
	PORTAL(2, "官网"),
	/**移动端-3*/
	MOBILE(3, "移动APP端"),
	/**微信端-4*/
	WECHAT(4, "微信客户端"),
	// 公司自建的角色，只有公司角色登录的用户可见
	COMPANY(5, "公司管理员"),
	PAD(6, "一体机");
	/**
	 * 编码-code
	 */
	private final Integer code;
	/**
	 * 描述-message
	 */
	private final String message;
	
	
	
	ScopeType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * code 编码
	 *
	 * @return 返回编码
	 */
	@Override
	public Integer code() {
		return code;
	}
	
	/**
	 * message 信息
	 *
	 * @return 返回信息
	 */
	@Override
	public String message() {
		return message;
	}
}
