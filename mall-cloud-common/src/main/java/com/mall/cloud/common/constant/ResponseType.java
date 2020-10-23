package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目ResponseType类.<br></p> 
 * <p>系统响应类型常量类<br></p>
 * @author Powered by marklin 2020-10-23 11:10
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public enum ResponseType implements BaseHandler {
	/**
	 * 参数校验不通过-806
	 */
	CHECK(806, "请求参数校验不通过!"),
	/**
	 * 请求参数为空-801
	 */
	EMPTY(801, "请求参数为空!"),
	/**
	 * 错误-404
	 */
	ERROR(404, "请求参数错误!"),
	/**
	 * 用户token过期-800
	 */
	EXCEPTION(802, "用户操作异常!"),
	/**
	 * 操作异常-802
	 */
	EXPIRE(800, "用户Token过期!"),
	/**
	 * 失败-500
	 */
	FAILURE(500, "请求失败!"),
	/**
	 * 当前操作不可用-807
	 */
	FORBIDDEN(807, "当前操作不可用!"),
	/**
	 * 身份证号码无效-804
	 */
	ILLEGAL(804, "身份证号码无效!"),
	/**
	 * 验证码无效-803
	 */
	INVALID(803, "短信验证码无效!"),
	/**
	 * 查询数据失败-900
	 */
	QUERY(900, "查询数据失败!"),
	/**
	 * 拒绝-501
	 */
	REJECT(501, "调用方法异常!"),
	/**
	 * 用户操作重复-805
	 */
	REPEAT(805, "用户操作重复!"),
	/**
	 * 保存数据失败-808
	 */
	SAVE(808, "保存数据失败!"),
	/**
	 * 成功-200
	 */
	SUCCESS(200, "请求成功!"),
	/**
	 * 更新数据失败-809
	 */
	UPDATE(809, "更新数据失败!"),
	;
	
	/**
	 * 编码-code
	 */
	private final Integer code;
	/**
	 * 描述-message
	 */
	private final String message;
	
	ResponseType(Integer code, String message) {
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
