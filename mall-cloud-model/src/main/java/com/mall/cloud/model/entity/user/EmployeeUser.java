package com.mall.cloud.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import javax.validation.constraints.*;

/**
 * <p>封装Qicloud项目EmployeeUser类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:14
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_employee_user")
public class EmployeeUser extends BaseEntity {
    private static final long serialVersionUID = -623383327906325862L;
    /**
     * 员工id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 员工账户
     */
    private String account;
    /**
     * 员工密码
     */
    private String password;

    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工昵称
     */
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
    private Integer profilePicture;

    /**
     * 账户类型[0-系统管理员 1-公司管理员]
     */
    private Integer type;
}
