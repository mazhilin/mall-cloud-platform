package com.mall.cloud.model.entity.sytem;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

/**
 * <p>封装Qicloud项目RoleItem类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:22
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_role_item")
public class RoleItem extends BaseEntity {
    private static final long serialVersionUID = -8801683249585431164L;
    /**
     * 角色项id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 角色id
     */
    private String roleId;
}
