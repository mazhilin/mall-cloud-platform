package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目ScopeType类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-24 00:34
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum ScopeType implements BaseHandler {
	/**
	 * 公共平台-0
	 */
	COMMON(0, "公共平台"),
	/**
	 * 后端平台-1
	 */
	CONSOLE(1, "后端平台"),
	/**
	 * 前端平台-2
	 */
	FRONT(2, "前端平台"),
	/**
	 * 官网平台-3
	 */
	PORTAL(3, "官网平台"),
	/**
	 * 移动端-4
	 */
	MOBILE(4, "移动APP端"),
	/**
	 * 微信端-5
	 */
	WECHAT(5, "微信SMR端"),
	/**
	 * 公司管理员-6
	 */
	COMPANY(6, "公司管理员"),
	/**
	 * 公司自定义-7
	 */
	CUSTOMIZE(7, "公司自定义");
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
