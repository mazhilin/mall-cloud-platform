package com.mall.cloud.passport.api.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.mall.cloud.common.constant.Formats;
import com.mall.cloud.common.persistence.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>封装Qicloud项目RequestUserParam类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-18 15:17
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
public class RequestUserParam extends BaseParam {
    private static final long serialVersionUID = 9121211643224359293L;
    /**
     * 用户账户
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户性别类型[0-保密 1-男 2-女]
     */
    private String email;
    /**
     * 用户性别类型[0-保密 1-男 2-女]
     */
    private Integer gender;

    /**
     * 用户性别类型[0-保密 1-男 2-女]
     */
    private String profilePicture;

    /**
     * 账户类型[0-系统管理账户 1-公司管理账户 2-公司员工账户]
     */
    private Integer type;
    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    @DateTimeFormat(pattern = Formats.DATE_TIME_TO_PM)
    @JSONField(format = Formats.DATE_TIME_TO_PM)
    private LocalDateTime loginTime;
}
