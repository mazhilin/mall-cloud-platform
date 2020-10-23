package com.mall.cloud.common.constant;

/**
 * <p>封装Qicloud项目RejectedExecutions类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 22:34
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public enum RejectedType {
	CALLER_RUNS_POLICY("CallerRunsPolicy"),
	ABORT_POLICY("AbortPolicy"),
	DISCARD_POLICY("DiscardPolicy"),
	DISCARD_OLDEST_POLICY("DiscardOldestPolicy");
	
	RejectedType(String type) {
		this.type = type;
	};
	
	private String type;
	
	public String getType() {
		return type;
	}
	
	public static boolean exists(String type) {
		for (RejectedType typeEnum : RejectedType.values()) {
			if (typeEnum.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
