package com.mall.cloud.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

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
    private String account;
    /**
     * 客户密码
     */
    private String password;

    /**
     * 客户姓名
     */
    private String name;
    /**
     * 客户昵称
     */
    private String nickname;
}
