package com.mall.cloud.model.entity.monitor;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * <p>封装Qicloud项目RequestInfo类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:27
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_request_info")
public class RequestInfo extends BaseEntity {
    private static final long serialVersionUID = -2680658918099498222L;
    /**
     * 请求id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 请求用户id
     */
    private String requestUserId;
    /**
     * 请求用户类型
     */
    private String requestUserType;
    /**
     * 请求服务id
     */
    private String serviceId;
    /**
     * 请求Token
     */
    private String token;
    /**
     * 请求IP
     */
    private String ipAddress;
    /**
     * 请求客户端类型[web-Web客户端 app-APP smr-小程序]
     */
    private String clientType;
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
     * 请求API服务URL
     */
    private String apiAddress;
    /**
     * 请求错误信息
     */
    private String errorMessage;
    /**
     * 请求参数
     */
    private String inputParam;
    /**
     * 请求响应结果
     */
    private String returnInfo;
    /**
     * 请求响应结果
     */
    private LocalDateTime responseTime;
    /**
     * 请求响应结果
     */
    private LocalDateTime requestTime;

}
