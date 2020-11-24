package com.mall.cloud.model.entity.file;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.cloud.common.persistence.entity.BaseEntity;
import lombok.*;

/**
 * <p>封装Qicloud项目FileUpload类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-24 22:10
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_file_upload")
public class FileUpload extends BaseEntity {
    private static final long serialVersionUID = 3433072887905305366L;
    /**
     * 文件ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 源文件文件名
     */
    private String sourceName;
    /**
     * 文件存储路径
     */
    private String path;
    /**
     * 文件访问地址
     */
    private String url;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 图片高
     */
    private Integer height;
    /**
     * 图片宽
     */
    private Integer width;
}
