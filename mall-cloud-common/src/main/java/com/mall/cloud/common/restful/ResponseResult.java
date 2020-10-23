package com.mall.cloud.common.restful;

import java.util.Map;

import com.mall.cloud.common.constant.ResponseType;
import com.mall.cloud.common.persistence.result.Result;

import lombok.Data;

/**
 * <p>封装Qicloud项目ResponseResult类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 11:07
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Data
public class ResponseResult implements Result {
	private static final long serialVersionUID = -3252379463288262335L;
	/**
	 * 是否成功标识-success[true-是,false-否]
	 */
	private boolean success = Boolean.TRUE;
	/**
	 * 状态编码-code[200-成功,500-失败]
	 */
	private int code = ResponseType.SUCCESS.code();
	/**
	 * 状态信息-message
	 */
	private String message = ResponseType.SUCCESS.message();
	/**
	 * 数据集合-result
	 */
	private Map<String, Object> result;
	/**
	 * 请求时间戳-result
	 */
	private long timestamp;
	/**
	 * 请求实例-instanceId
	 */
	private long instanceId;
}
