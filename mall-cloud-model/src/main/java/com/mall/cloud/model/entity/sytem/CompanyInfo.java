package com.mall.cloud.model.entity.sytem;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

/**
 * <p>封装Qicloud项目CompanyInfo类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 22:59
 * @version 1.0.0 mall_category_info
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_company_info")
public class CompanyInfo extends BaseEntity {
    private static final long serialVersionUID = -5995047057965174633L;
    /**
     * 公司id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 公司编码
     */
    private String code;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 公司简介
     */
    private String introduction;
    /**
     * 公司轮播图
     */
    private String infoPictures;
    /**
     * 公司视频
     */
    private String infoVideos;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 上级公司Id
     */
    private String parentId;
    /**
     * 门户官网
     */
    private String portalWebsite;
    /**
     * 地址
     */
    private String address;
}
