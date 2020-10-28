package com.mall.cloud.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import javax.validation.constraints.*;

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
     * 账户类型[0-系统管理员 1-公司管理员]
     */
    private Integer type;
    /**
     * 用户手机号
     */
    @NotNull(message = "手机号不能为空")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp ="^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Max(value = 11,message = "手机号只能为{max}位")
    @Min(value = 11,message = "手机号只能为{min}位")
    private String phone;

}