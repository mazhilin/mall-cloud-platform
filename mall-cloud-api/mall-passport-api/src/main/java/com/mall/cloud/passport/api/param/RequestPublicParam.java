package com.mall.cloud.passport.api.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mall.cloud.common.persistence.param.BaseParam;
import lombok.Data;

/**
 * <p>封装Qicloud项目RequestPublicParam类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-21 23:53
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
public class RequestPublicParam extends BaseParam {
    private static final long serialVersionUID = -3499676683023556309L;
    /**
     * 配置id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 配置图标
     */
    private String icon;
    /**
     * 配置类型[0-系统平台  1-开放平台 2-其他平台]
     */
    private String type;
    /**
     * 配置编码
     */
    private String code;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 配置信息
     */
    private String message;
    /**
     * 配置authorizeUrl
     */
    private String authorizeUrl;
    /**
     * 配置码值
     */
    private String value;
    /**
     * 配置appId
     */
    private String appId;
    /**
     * 配置appSecret
     */
    private String appSecret;
    /**
     * 配置endpointUrl
     */
    private String endpointUrl;
    /**
     * 配置redirectUrl
     */
    private String redirectUrl;
    /**
     * 配置作用域
     */
    private String scope;
}
