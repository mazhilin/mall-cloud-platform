package com.mall.cloud.model.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.constant.Formats;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * <p>封装Qicloud项目AdminUser类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 21:52
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_admin_user")
public class AdminUser extends BaseEntity {
    private static final long serialVersionUID = 3854519652448554552L;
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 用户账户
     */
    @NotBlank(message = "用户账户不能为空!")
    @NotNull(message = "用户账户不能为空!")
    @NotEmpty(message = "用户账户不能为空!")
    private String account;
    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码不能为空!")
    @NotNull(message = "用户密码不能为空!")
    @NotEmpty(message = "用户密码不能为空!")
    private String password;

    /**
     * 用户姓名
     */
    @NotBlank(message = "用户姓名不能为空!")
    @NotNull(message = "用户姓名不能为空!")
    @NotEmpty(message = "用户姓名不能为空!")
    private String name;
    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空!")
    @NotNull(message = "用户昵称不能为空!")
    @NotEmpty(message = "用户昵称不能为空!")
    private String nickname;
    /**
     * 用户手机号
     */
    @NotNull(message = "手机号不能为空")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Max(value = 11, message = "手机号只能为{max}位")
    @Min(value = 11, message = "手机号只能为{min}位")
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
