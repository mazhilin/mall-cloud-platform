package com.mall.cloud.common.constant;

/**
 * <p>封装Qicloud项目QueueType类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 22:36
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public enum  QueueType {
	
	LINKED_BLOCKING_QUEUE("LinkedBlockingQueue"),
	SYNCHRONOUS_QUEUE("SynchronousQueue"),
	ARRAY_BLOCKING_QUEUE("ArrayBlockingQueue"),
	DELAY_QUEUE("DelayQueue"),
	LINKED_TRANSFER_DEQUE("LinkedTransferQueue"),
	LINKED_BLOCKING_DEQUE("LinkedBlockingDeque"),
	PRIORITY_BLOCKING_QUEUE("PriorityBlockingQueue");
	
	QueueType(String type) {
		this.type = type;
	};
	
	private String type;
	
	public String getType() {
		return type;
	}
	
	public static boolean exists(String type) {
		for (QueueType typeEnum : QueueType.values()) {
			if (typeEnum.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
