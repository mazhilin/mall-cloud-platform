package com.mall.cloud.common.properties;

import com.mall.cloud.common.constant.QueueType;
import com.mall.cloud.common.constant.RejectedType;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目ThreadPoolProperties类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author marklin
 * @author Powered by marklin 2020-10-23 22:32
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Data
public class ThreadPoolProperties {
	/**
	 * 线程池名称
	 */
	private String threadPoolName = "MallCloudThreadPool";
	
	/**
	 * 核心线程数
	 */
	private int corePoolSize = 1;
	
	/**
	 * 最大线程数, 默认值为CPU核心数量
	 */
	private int maximumPoolSize = Runtime.getRuntime().availableProcessors();
	
	/**
	 * 队列最大数量
	 */
	private int queueCapacity = Integer.MAX_VALUE;
	
	/**
	 * 队列类型
	 * @see QueueType
	 */
	private String queueType = QueueType.LINKED_BLOCKING_QUEUE.getType();
	
	/**
	 * SynchronousQueue 是否公平策略
	 */
	private boolean fair;
	
	/**
	 * 拒绝策略
	 * @see RejectedType
	 */
	private String rejectedExecutionType = RejectedType.ABORT_POLICY.getType();
	
	/**
	 * 空闲线程存活时间
	 */
	private long keepAliveTime;
	
	/**
	 * 空闲线程存活时间单位
	 */
	private TimeUnit unit = TimeUnit.MILLISECONDS;
	
	/**
	 * 队列容量阀值，超过此值告警
	 */
	private int queueCapacityThreshold = queueCapacity;
}
