package com.mall.cloud.common.restful;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mall.cloud.common.persistence.result.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * <p>封装Qicloud项目DatagridResult类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 11:21
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Data
@Builder
@ToString
@AllArgsConstructor
public class DatagridResult implements Result {
	private static final long serialVersionUID = -954766243250722604L;
	/**
	 * 数据列表总数-totalCount
	 */
	private Long totalCount = 0L;
	/**
	 * 页数-pageSize
	 */
	private Integer pageSize = 1;
	/**
	 * 页条目数-pageCount
	 */
	private Integer pageCount = 10;
	/**
	 * 总页数-totalPage
	 */
	private Long totalPage = 0L;
	/**
	 * 返回数据列表-dataList
	 */
	private List<?> dataList;
	/**
	 * 返回数据集合-dataMap
	 */
	private Map<String, Object> dataMap;
	/**
	 * 返回其它数据-otherData
	 */
	private Object otherData;
	
	public DatagridResult(int pageSize, int pageCount, Long totalCount) {
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		this.dataList = Lists.newArrayList();
		long totalPage;
		if (totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		} else {
			totalPage = totalCount / pageCount + 1;
		}
		this.totalPage = totalPage;
	}
	
	public DatagridResult(int pageSize, int pageCount, PageInfo<?> pageInfo) {
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		long totalCount = pageInfo.getTotal();
		this.dataList = pageInfo.getList();
		this.totalCount = totalCount;
		long totalPage;
		if (totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		} else {
			totalPage = totalCount / pageCount + 1;
		}
		this.totalPage = totalPage;
	}
	
	public DatagridResult(List<?> dataList) {
		this.dataList = dataList;
	}
}
