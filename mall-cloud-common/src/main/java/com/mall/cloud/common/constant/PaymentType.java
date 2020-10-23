package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目PaymentType类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 20:37
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public enum PaymentType implements BaseHandler {
	/**
	 * 微信支付-0
	 */
	WECHAT(0, "微信支付"),
	/**
	 * 支付宝-1
	 */
	ALI(1, "支付宝"),
	/**
	 * 银联支付-2
	 */
	UNION(2, "银联支付"),
	/**
	 * 京东支付-3
	 */
	JD(3, "京东支付"),
	/**
	 * 线下支付-3
	 */
	OFFLINE(3, "线下支付"),
	;
	
	/**
	 * 编码-code
	 */
	private final Integer code;
	/**
	 * 描述-message
	 */
	private final String message;
	
	PaymentType(Integer code, String message) {
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
