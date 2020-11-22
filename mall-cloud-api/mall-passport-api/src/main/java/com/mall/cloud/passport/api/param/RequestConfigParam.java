package com.mall.cloud.passport.api.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mall.cloud.common.persistence.param.BaseParam;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>封装Qicloud项目RequestConfigParam类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-21 22:21
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
public class RequestConfigParam extends BaseParam {
    private static final long serialVersionUID = -3824511626508749858L;
    /**
     * 配置id
     */
    private String id;
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
     * 配置码值
     */
    private String value;
    /**
     * 配置作用域
     */
    private String scope;
}
