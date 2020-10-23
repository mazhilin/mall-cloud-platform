package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目MemberType类.<br></p>
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 21:08
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public enum MemberType implements BaseHandler {
	/**普通会员-0*/
	GENERAL(0,"普通会员"),
	/**初级会员-1*/
	PRIMARY(1,"初级会员"),
	/**中级会员-2*/
	INTERMEDIATE(2,"中级会员"),
	/**高级会员-3*/
	ADVANCED(3,"高级会员"),;
	
	/**
	 * 编码-code
	 */
	private final Integer code;
	/**
	 * 描述-message
	 */
	private final String message;
	
	
	
	MemberType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * code 编码
	 *
	 * @return 返回编码
	 */
	@Override public Integer code() {
		return code;
	}
	
	/**
	 * message 信息
	 *
	 * @return 返回信息
	 */
	@Override public String message() {
		return message;
	}
}
