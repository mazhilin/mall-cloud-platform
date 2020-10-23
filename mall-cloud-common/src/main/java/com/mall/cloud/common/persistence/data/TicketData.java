package com.mall.cloud.common.persistence.data;

import lombok.*;

import java.io.Serializable;

/**
 * <p>封装Qicloud项目TicketData类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 11:01
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TicketData implements Serializable {
	private static final long serialVersionUID = -1246010420313725490L;
	/** dataId */
	private String dataId;
	
	/** 是否记住我 */
	private Boolean rememberMe;
	
	/** 登录类型：1：web端，2：app端 */
	private Integer clientType;
}
