package com.mall.cloud.model.entity.sytem;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

/**
 * <p>封装Qicloud项目RegionInfo类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 23:19
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_region_info")
public class RegionInfo  extends BaseEntity {
    private static final long serialVersionUID = 4897187522001545156L;
    /**
     * 区域id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父级区域id
     */
    private Long parentId;
    /**
     * 行政区域等级 1-省 2-市 3-区县 4-街道镇
     */
    private Integer level;
    /**
     * 行政区域编码
     */
    private String regionCode;
    /**
     * 行政区域名称
     */
    private String regionName;
    /**
     * 行政区域完整名称
     */
    private String wholeName;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 电话区号
     */
    private String phoneCode;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 区域全拼
     */
    private String fullName;
    /**
     * 区域简拼
     */
    private String simpleName;
    /**
     * 区域首字母
     */
    private String initialsName;
    /**
     * 数据来源[0-系统默认 1-系统新增]
     */
    private Integer sourceType;
}
