package com.mall.cloud.common.persistence.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>封装Qicloud项目StorageFileParam类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-24 13:59
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
public class StorageFileParam implements Param {
    private static final long serialVersionUID = -997702894789171318L;
    /**
     * 旧文件名
     */
    private String name;
    /**
     * 源文件文件名
     */
    private String sourceName;
    /**
     * 文件扩展名
     */
    private String extName;
    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 文件转为字节集
     */
    private MultipartFile file;
    /**
     * 大小
     */
    private Long size;
    /**
     * 高
     */
    private Integer height;
    /**
     * 宽
     */
    private Integer width;
    /**
     * 操作人
     */
    private String userId;
    /**
     * 项目类型
     */
    private String type;
    /**
     * 项目类型
     */
    private String fileType;

    private byte[] bytes;
}
