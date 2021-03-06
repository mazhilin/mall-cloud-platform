package com.mall.cloud.common.persistence.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.mall.cloud.common.constant.Formats;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>封装Qicloud项目BaseEntity类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 10:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity  implements Entity {
	private static final long serialVersionUID = -847020715802664453L;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = Formats.DATE_TIME_TO_PM)
	@JSONField(format = Formats.DATE_TIME_TO_PM)
	private LocalDateTime createTime;
	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	@DateTimeFormat(pattern = Formats.DATE_TIME_TO_PM)
	@JSONField(format = Formats.DATE_TIME_TO_PM)
	private LocalDateTime updateTime;
	/**
	 * 是否删除[0-否 1-是]
	 */
	@ApiModelProperty(value = "是否删除[0-否 1-是]")
	private Integer isDelete;
	/**
	 * 系统状态-[0-禁用 1-启用 2-移除]
	 */
	@ApiModelProperty(value = "系统状态-[0-禁用 1-启用 2-移除]")
	private Integer status;
	/**
	 * 备注描述-remark
	 */
	@ApiModelProperty(value = "备注描述")
	private String remark;
}
