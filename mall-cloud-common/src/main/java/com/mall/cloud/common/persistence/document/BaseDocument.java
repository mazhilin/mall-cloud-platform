package com.mall.cloud.common.persistence.document;

import com.alibaba.fastjson.annotation.JSONField;
import com.mall.cloud.common.constant.Formats;
import com.mall.cloud.common.persistence.document.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>封装Qicloud项目BaseDocument类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 10:21
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDocument implements Document {
	private static final long serialVersionUID = 8282365920137665834L;
	/**
	 * 创建人-createBy
	 */
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**
	 * 创建时间-createDate
	 */
	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = Formats.DATE_TIME_TO_PM)
	@JSONField(format = Formats.DATE_TIME_TO_PM)
	private LocalDateTime createTime;
	/**
	 * 更新人-updateBy
	 */
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**
	 * 更新时间-updateDate
	 */
	@ApiModelProperty(value = "更新时间")
	@DateTimeFormat(pattern = Formats.DATE_TIME_TO_PM)
	@JSONField(format = Formats.DATE_TIME_TO_PM)
	private LocalDateTime updateTime;
	/**
	 * 系统状态-status
	 */
	@ApiModelProperty(value = "系统状态-[0-禁用 1-启用 2-删除]")
	private Integer status;
	/**
	 * 备注描述-remark
	 */
	@ApiModelProperty(value = "备注描述")
	private String remark;
}
