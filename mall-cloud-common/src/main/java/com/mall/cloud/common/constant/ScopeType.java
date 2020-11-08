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
	 * 后端平台-0
	 */
	WEB(0, "后端平台"),
	/**
	 * 移动端-1
	 */
	APP(1, "移动APP端"),
	/**
	 * 微信端-2
	 */
	SMR(2, "微信SMR端");
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
