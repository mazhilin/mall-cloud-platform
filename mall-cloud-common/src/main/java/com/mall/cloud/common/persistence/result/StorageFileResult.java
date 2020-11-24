package com.mall.cloud.common.persistence.result;

import lombok.Data;

/**
 * 封装Qicloud项目StorageFileResult类.<br>
 *
 * <p>//TODO...<br>
 *
 * @author Powered by marklin 2020-11-24 11:45
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.
 * <br>
 */
@Data
public class StorageFileResult implements Result {
    private static final long serialVersionUID = -9110295432563848030L;
    private String fileName;
    private String fileUrl;

    public StorageFileResult(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
