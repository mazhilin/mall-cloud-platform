package com.mall.cloud.common.constant;

import com.mall.cloud.common.BaseHandler;

/**
 * <p>封装Qicloud项目StorageType类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 01:47
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum StorageType implements BaseHandler {
    /**
     * Minio对象存储-0
     */
    MINIO(0, "Minio"),
    /**
     * 阿里云OSS-1
     */
    ALIOSS(1, "阿里云OSS"),
      ;

    /**
     * 编码-code
     */
    private final Integer code;
    /**
     * 描述-message
     */
    private final String message;

    StorageType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * code 编码
     *
     * @return 返回编码
     */
    @Override
    public Integer code() {
        return code;
    }

    /**
     * message 信息
     *
     * @return 返回信息
     */
    @Override
    public String message() {
        return message;
    }
}
