package com.mall.cloud.passport.api.service;

import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.param.StorageFileParam;
import com.mall.cloud.common.persistence.service.BaseService;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.model.entity.file.FileUpload;

import java.util.Date;

/**
 * <p>封装Qicloud项目StorageFileService类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-24 00:57
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
public interface StorageServerService extends BaseService {




    ResponseResult createBucket(ResponseResult result,String bucketName) throws PassportServerException;

    /**
     * minio存储文件64
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload minioStorageBase64(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;


    /**
     *minio存储文件
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload minioStorageFile(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;

    /**
     *minio存储文件
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload minioStorageFiles(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;

    /**
     *minio存储文件字节
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload minioStorageBytes(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;

    /**
     * 阿里云存储文件
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload ossStorageFile(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;

    /**
     * 阿里云存储大文件
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload ossStorageFiles(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;

    /**
     * 阿里云存储文件64
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload ossStorageBase64(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;

    /**
     * 阿里云存储文件64
     * @param fileParam 文件参数
     * @param bucketName 存储空间
     * @param environment  平台环境
     * @return 存储结果
     * @throws PassportServerException 异常消息
     */
    FileUpload ossStorageBytes(StorageFileParam fileParam,String bucketName, Boolean environment) throws PassportServerException;



}
