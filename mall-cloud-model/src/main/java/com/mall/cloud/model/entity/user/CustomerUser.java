package com.mall.cloud.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import javax.validation.constraints.*;

/**
 * <p>封装Qicloud项目CustomerUser类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:07
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_customer_user")
public class CustomerUser extends BaseEntity {

    private static final long serialVersionUID = -8328262567577275585L;
    /**
     * 客户id
     */
    private String id;
    /**
     * 客户账户
     */
    @NotBlank(message = "用户账户不能为空!")
    @NotNull(message = "用户账户不能为空!")
    @NotEmpty(message = "用户账户不能为空!")
    private String account;
    /**
     * 客户密码
     */
    @NotBlank(message = "用户账户不能为空!")
    @NotNull(message = "用户账户不能为空!")
    @NotEmpty(message = "用户账户不能为空!")
    private String password;

    /**
     * 客户姓名
     */
    @NotBlank(message = "用户账户不能为空!")
    @NotNull(message = "用户账户不能为空!")
    @NotEmpty(message = "用户账户不能为空!")
    private String name;
    /**
     * 客户昵称
     */
    @NotBlank(message = "用户账户不能为空!")
    @NotNull(message = "用户账户不能为空!")
    @NotEmpty(message = "用户账户不能为空!")
    private String nickname;
    /**
     * 客户手机号
     */
    @NotNull(message = "手机号不能为空")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp ="^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Max(value = 11,message = "手机号只能为{max}位")
    @Min(value = 11,message = "手机号只能为{min}位")
    private String phone;
}
