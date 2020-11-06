package com.mall.cloud.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

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
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 客户id
     */
    private String customerUserId;
    /**
     * 用户生日
     */
    private LocalDateTime birthday;
    /**
     * 公司id
     */
    private String identityCard;
    /**
     * 省/直辖市-编码
     */
    private String provinceCode;
    /**
     * 省/直辖市-名称
     */
    private String provinceName;
    /**
     * 市/区-编码
     */
    private String cityCode;
    /**
     * 市/区-名称
     */
    private String cityName;
    /**
     * 区/县-编码
     */
    private String countyCode;
    /**
     * 区/县-名称
     */
    private String countyName;
    /**
     * 具体地址
     */
    private String location;
    /**
     * 完整地址
     */
    private String address;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
}
