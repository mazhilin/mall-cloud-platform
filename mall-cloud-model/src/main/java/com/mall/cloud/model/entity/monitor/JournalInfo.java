package com.mall.cloud.model.entity.monitor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 封装Qicloud项目JournalInfo类.<br>
 *
 * <p>//TODO...<br>
 *
 * @author Powered by marklin 2020-10-28 22:49
 * @version 1.0.0
 *     <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.
 *     <br>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_journal_info")
public class JournalInfo extends BaseEntity {
    private static final long serialVersionUID = 3258355806982380917L;
    /**
     * 日志id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台类型[web-Web客户端 app-APP smr-小程序]
     */
    private String platformType;
    /**
     * 请求方法名称
     */
    private String methodName;
    /**
     * 业务操作类型
     */
    private String businessType;
    /**
     * 来源类型[0-公共平台 1-后端平台]
     */
    private String sourceType;
    /**
     * 请求方法类型
     */
    private String requestType;
    /**
     * 请求设备类型
     */
    private String deviceType;
    /**
     * 请求设备名称
     */
    private String deviceName;
    /**
     * 请求设备唯一码
     */
    private String deviceCode;
    /**
     * 请求操作系统
     */
    private String systemVersion;
    /**
     * 请求IP地址
     */
    private String ipAddress;
    /**
     * 请求API服务URL
     */
    private String apiAddress;
    /**
     * 应用服务id
     */
    private String serviceId;
    /**
     * 请求参数
     */
    private String inputParam;
    /**
     * 响应结果
     */
    private String returnInfo;
    /**
     * 请求时间
     */
    private LocalDateTime requestTime;
    /**
     * 响应时间
     */
    private LocalDateTime responseTime;
    /**
     * 日志类型[0-成功记录 1-错误异常]
     */
    private Integer messageType;
}
