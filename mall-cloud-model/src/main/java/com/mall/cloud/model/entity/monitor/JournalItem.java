package com.mall.cloud.model.entity.monitor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

/**
 * <p>封装Qicloud项目JournalItem类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 21:01
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_journal_item")
public class JournalItem extends BaseEntity {
    private static final long serialVersionUID = -2467295307548328071L;
    /**
     * 日志信息id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 日志id
     */
    private String journalId;
    /**
     * 功能模块
     */
    private String title;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 操作日志信息[对应日志类型]
     */
    private String messageInfo;
}
