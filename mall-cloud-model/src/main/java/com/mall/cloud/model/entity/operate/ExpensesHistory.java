package com.mall.cloud.model.entity.operate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import java.math.BigDecimal;

/**
 * <p>封装Qicloud项目ExpensesHistory类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-29 21:48
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_expenses_history")
public class ExpensesHistory extends BaseEntity {
    private static final long serialVersionUID = 223197210485709613L;
    /**
     * 业绩id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 员工id
     */
    private String employeeId;
    /**
     * 员工姓名
     */
    private String employeeName;
    /**
     * 邀约量
     */
    private Integer invite;
    /**
     * 成交量
     */
    private Integer success;

    /**
     * 客户ID
     */
    private String customerId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 会员充值
     */
    private BigDecimal recharge;
    /**
     * 平台购物
     */
    private BigDecimal payment;
    /**
     * 消费总额
     */
    private BigDecimal consume;
    /**
     * 员工业绩
     */
    private BigDecimal achievement;
}
