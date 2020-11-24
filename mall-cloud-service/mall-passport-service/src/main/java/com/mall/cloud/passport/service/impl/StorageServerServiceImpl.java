package com.mall.cloud.passport.service.impl;


import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.param.StorageFileParam;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.StorageServerUtil;
import com.mall.cloud.model.entity.file.FileUpload;
import com.mall.cloud.passport.api.service.StorageServerService;
import lombok.AllArgsConstructor;


/**
 * <p>封装Qicloud项目StorageServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-23 17:08
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
@AllArgsConstructor
public class StorageServerServiceImpl extends BaseServerService implements StorageServerService {


    @Override
    public ResponseResult createBucket(ResponseResult result, String bucketName) throws PassportServerException {
        return null;
    }

    /**
     * minio存储文件64
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload minioStorageBase64(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }

    /**
     * minio存储文件
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload minioStorageFile(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }

    /**
     * minio存储文件
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload minioStorageFiles(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }

    /**
     * minio存储文件字节
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload minioStorageBytes(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }

    /**
     * 阿里云存储文件
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload ossStorageFile(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }

    /**
     * 阿里云存储大文件
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload ossStorageFiles(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }

    /**
     * 阿里云存储文件64
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload ossStorageBase64(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }

    /**
     * 阿里云存储文件64
     *
     * @param fileParam   文件参数
     * @param bucketName  存储空间
     * @param environment 平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public FileUpload ossStorageBytes(StorageFileParam fileParam, String bucketName, Boolean environment) throws PassportServerException {
        return null;
    }
}
