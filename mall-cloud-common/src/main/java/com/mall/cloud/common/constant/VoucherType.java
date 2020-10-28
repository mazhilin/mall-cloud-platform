package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目VoucherType类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-23 21:02
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum VoucherType implements BaseHandler {
	/**通用券-拥有以上所有功能*/
	COMMON(1, "通用券"),
	/**换购券——消费者持换购券可换购指定商品*/
	CDKEY(2, "兑换券"),
	/**现金券——消费者持券消费可抵用部分现金*/
	CASH(3, "现金券"),
	/**体验券——消费者持券消费可体验部分服务*/
	FREE(4, "体验券"),
	/**礼品券——消费者持券消费可领用指定礼品*/
	GIFT(5, "礼品券"),
	/**折扣券——消费者持券消费可享受消费折扣*/
	DISC(6, "折扣券"),
	/**特价券——消费者持券消费可购买特价商品*/
	SPEC(7, "特价券"),
	;

	/**
	 * 编码-code
	 */
	private final Integer code;
	/**
	 * 描述-message
	 */
	private final String message;


	VoucherType(Integer code, String message) {
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
