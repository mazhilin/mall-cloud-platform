package com.mall.cloud.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

/**
 * <p>封装Qicloud项目EmployeeInfo类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:17
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_employee_info")
public class EmployeeInfo extends BaseEntity {
    private static final long serialVersionUID = -110174285340761845L;
    /**
     * 员工信息id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 员工id
     */
    private String employeeId;
}
