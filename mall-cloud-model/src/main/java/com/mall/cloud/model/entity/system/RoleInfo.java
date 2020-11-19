package com.mall.cloud.model.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import java.math.BigDecimal;

/**
 * <p>封装Qicloud项目RoleInfo类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:20
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_role_info")
public class RoleInfo extends BaseEntity {
    private static final long serialVersionUID = -5582288879380665488L;
    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 角色作用域[1-后台 2-APP 3-SMR 4-公司管理员]
     */
    private Integer scope;
    /**
     * 公司id
     */
    private String companyId;
}
