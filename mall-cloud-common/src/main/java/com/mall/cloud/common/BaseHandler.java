package com.mall.cloud.common;

/**
 * <p>封装Qicloud项目BaseHandler类.<br></p> 
 * <p>封装基础Handler接口服务<br></p>
 * @author Powered by marklin 2020-10-23 11:15
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public interface BaseHandler {
	/**
	 * code 编码
	 *
	 * @return 返回编码
	 */
	Integer code();
	
	/**
	 * message 信息
	 *
	 * @return 返回信息
	 */
	String message();
}
