package com.mall.cloud.passport.api.param;

import com.mall.cloud.common.persistence.param.BaseParam;
import lombok.Data;

/**
 * <p>封装Qicloud项目RequestRoleParam类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-22 16:00
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
public class RequestRoleParam extends BaseParam {
    private static final long serialVersionUID = 8420951750210120665L;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色编码
     */
    private String name;
    /**
     * 角色描述
     */
    private String message;
    /**
     * 作用域[1-后台 2-APP 3-SMR 4-公司管理员]
     */
    private Integer scope;
    /**
     * 公司id
     */
    private String companyId;
}
