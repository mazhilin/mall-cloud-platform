package com.mall.cloud.common.restful;

import com.google.common.collect.Maps;
import com.mall.cloud.common.constant.ResponseType;
import com.mall.cloud.common.persistence.result.Result;
import com.mall.cloud.common.utils.JsonServerUtil;
import com.mall.cloud.common.utils.SequenceServerUtil;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
	 * 返回数据列表-dataList
	 */
	private List<?> otherList;
	/**
	 * 返回其它数据-otherData
	 */
	private Object otherData;
	/**
	 * 请求时间戳-result
	 */
	private long timestamp;
	/**
	 * 请求实例-instanceId
	 */
	private long instanceId;

	public ResponseResult() {
		this.timestamp = Instant.now().toEpochMilli();
		this.instanceId = SequenceServerUtil.getInstance().produceId();
	}

	/**
	 * 设置错误信息[自定义]
	 *
	 * @param code    编码
	 * @param message 错误信息
	 */
	public void setError(Integer code, String message) {
		this.success = Boolean.FALSE;
		this.code = code;
		this.message = message;
		this.timestamp = Instant.now().toEpochMilli();
		this.instanceId = SequenceServerUtil.getInstance().produceId();
	}

	/**
	 * 设置成功返回列表数据
	 *
	 * @param data 数据结果
	 */
	public void setResult(DatagridResult data) {
		this.result.put("totalCount", data.getTotalCount());
		this.result.put("dataList", data.getDataList());
		if (Objects.nonNull(data.getPageSize())) {
			this.result.put("pageSize", data.getPageSize());
		}
		if (Objects.nonNull(data.getPageCount())) {
			this.result.put("pageSize", data.getPageCount());
		}
		if (Objects.nonNull(data.getTotalPage())) {
			this.result.put("totalPage", data.getTotalPage());
		}
	}

	/**
	 * @param key
	 * @param value
	 */
	public void putResult(String key, Object value) {
		Map<String, Object> result = getResult();
		if (Objects.isNull(result)) {
			result = Maps.newConcurrentMap();
		}
		result.put(key, value);
		setResult(result);
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	/**
	 * 格式化Json字符串
	 *
	 * @param response 结果
	 * @return
	 */
	public synchronized String parseToJson(ResponseResult response) {
		if (Objects.isNull(response.getResult())) {
			response = new ResponseResult();
			response.setCode(ResponseType.FAILURE.code());
			response.setError("请求系统接口异常!");
			return JsonServerUtil.getInstance().parseToJson(response);
		}
		return JsonServerUtil.getInstance().parseToJson(response);
	}

	/**
	 * 设置错误信息[默认设置]
	 *
	 * @param message 错误信息
	 * @return
	 */
	public ResponseResult setError(String message) {
		this.success = Boolean.FALSE;
		this.code = ResponseType.FAILURE.code();
		this.message = message;
		this.timestamp = Instant.now().toEpochMilli();
		this.instanceId = SequenceServerUtil.getInstance().produceId();
		return null;
	}

	/**
	 * 设置错误信息[默认设置]
	 *
	 * @param message 错误信息
	 * @return
	 */
	public void setError(Integer code, String message, List<String> list) {
		this.success = Boolean.FALSE;
		this.code = code;
		this.message = message;
		this.timestamp = Instant.now().toEpochMilli();
		this.instanceId = SequenceServerUtil.getInstance().produceId();
		this.otherList = list;
	}

	/**
	 * 设置错误信息[默认设置]
	 *
	 * @param message 错误信息
	 * @return
	 */
	public ResponseResult setError(String message, List<String> list) {
		this.success = Boolean.FALSE;
		this.code = ResponseType.FAILURE.code();
		this.message = message;
		this.timestamp = Instant.now().toEpochMilli();
		this.instanceId = SequenceServerUtil.getInstance().produceId();
		this.otherList = list;
		return null;
	}

  public static void main(String[] args) {
	  //
	  ResponseResult responseResult =new ResponseResult();
	  responseResult.setCode(500);
    System.out.println(JsonServerUtil.getInstance().parseToJson(responseResult));
  }
}
