package com.mall.cloud.common.persistence.data;

import lombok.*;

import java.io.Serializable;

/**
 * <p>封装Qicloud项目TokenData类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 10:59
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
public class TokenData  implements Serializable {
	private static final long serialVersionUID = 8446355791612542789L;
	/** 用户id */
	private Long userId;
	
	/** 用户登录账户 */
	private String account;
	
	/** 用户登录名 */
	private String userName;
	
	/** 是否登录：1：登录状态，0：未登录 */
	private Integer isLogin;
	
	/** 用户可用状态：1：正常，0：禁用 */
	private Integer userEnable;
	
	/** 是否记住我 */
	private Boolean rememberMe;
	
	/** 登录类型：1：web端，2：app端 */
	private Integer clientType;
}
