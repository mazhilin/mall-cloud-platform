package com.mall.cloud.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

/**
 * <p>封装Qicloud项目CustomerInfo类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:09
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_customer_info")
public class CustomerInfo extends BaseEntity {
    private static final long serialVersionUID = 7584464869747300983L;
    /**
     * 客户信息id
     */
    private String id;

    /**
     * 客户id
     */
    private String customerId;
}
