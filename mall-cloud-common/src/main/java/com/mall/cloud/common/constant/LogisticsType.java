package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目LogisticsType类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 20:50
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public enum LogisticsType implements BaseHandler {
	/***/
	SF(0,"顺丰速运"),
	HT(1, "百世快递"),
	ZT(2, "中通快递"),
	ST(3, "申通快递"),
	YT(4, "圆通速递"),
	YD(5, "韵达速递"),
	YZ(6, "邮政快递"),
	EMS(7, "EMS快递"),
	TT(8, "天天快递"),
	JD(9, "京东快递"),
	UC(10, "优速快递"),
	DBL(11, "德邦快递"),
	ZJS(12, "宅急送"),
	TNT(13, "TNT快递"),
	OTHERS(14, "其他快递");
	/**
	 * 编码-code
	 */
	private final Integer code;
	/**
	 * 描述-message
	 */
	private final String message;
	
	LogisticsType(Integer code, String message) {
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
