package com.mall.cloud.common.utils;

import cn.hutool.core.lang.UUID;
import com.mall.cloud.common.persistence.result.StorageFileResult;
import com.mall.cloud.common.properties.StorageProperties;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>封装Qicloud项目MinioServerUtil类.<br></p>
 * <p>Minio文件上传服务工具类<br></p>
 *
 * @author Powered by marklin 2020-11-19 09:58
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Slf4j
@Component
@Configuration
@EnableConfigurationProperties({StorageProperties.class})
public class MinioServerUtil {

    private static final String SEPARATOR_DOT = ".";
    private static final String SEPARATOR_ACROSS = "-";
    private static final String SEPARATOR_STR = "";
    /**
     * 阿里云API的密钥Access Key ID
     */
    public static String accessKeyId = "admin";
    /**
     * 阿里云API的密钥Access Key Secret
     */
    public static String accessKeySecret = "123456@Abc";
    /**
     * 阿里云API的外网域名
     */
    public static String endpoint = "http://oss-cn-qingdao.aliyuncs.com";
    /**
     * 阿里云API的外网域名
     */
    public static String ecsEndpoint = "http://oss-cn-qingdao.aliyuncs.com";
    @Resource
    private MinioClient minioClient;

    /**
     * @param bucketName
     * @return boolean
     * @Description 判断 bucket是否存在
     * @author exe.wangtaotao
     * @date 2020/10/21 16:33
     */
    public boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(bucketName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    public void makeBucket(String bucketName) {
        try {
            boolean isExist = minioClient.bucketExists(bucketName);
            if (!isExist) {
                minioClient.makeBucket(bucketName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param bucketName
     * @return boolean
     * @Description 删除桶
     * @author exe.wangtaotao
     * @date 2020/10/21 16:46
     */
    public boolean removeBucket(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                // 有对象文件，则删除失败
                if (item.size() > 0) {
                    return false;
                }
            }
            // 删除存储桶，注意，只有存储桶为空时才能删除成功。
            minioClient.removeBucket(bucketName);
            flag = bucketExists(bucketName);
            if (!flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param
     * @return java.util.List<java.lang.String>
     * @Description 获取文件存储服务的所有存储桶名称
     * @author exe.wangtaotao
     * @date 2020/10/21 16:35
     */
    public List<String> listBucketNames() throws IllegalArgumentException, IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ErrorResponseException, XmlParserException, InvalidBucketNameException, InsufficientDataException, InternalException {
        List<Bucket> bucketList = listBuckets();
        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    /**
     * @param
     * @return java.util.List<io.minio.messages.Bucket>
     * @Description 列出所有存储桶
     * @author exe.wangtaotao
     * @date 2020/10/21 16:35
     */
    public List<Bucket> listBuckets() throws IllegalArgumentException, IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        return minioClient.listBuckets();
    }

    /**
     * @param bucketName
     * @return java.util.List<java.lang.String>
     * @Description 列出存储桶中的所有对象名称
     * @author exe.wangtaotao
     * @date 2020/10/21 16:39
     */
    public List<String> listObjectNames(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        }
        return listObjectNames;
    }

    /**
     * @param bucketName
     * @return java.lang.Iterable<io.minio.Result < io.minio.messages.Item>>
     * @Description 列出存储桶中的所有对象
     * @author exe.wangtaotao
     * @date 2020/10/21 16:39
     */
    public Iterable<Result<Item>> listObjects(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            return minioClient.listObjects(bucketName);
        }
        return null;
    }


    /**
     * @param bucketName
     * @param objectName
     * @param stream
     * @param options
     * @return java.lang.String
     * @Description 通过文件地址，保留原始文件名 文件上传
     * @author exe.wangtaotao
     * @date 2020/10/21 15:16
     */
    public StorageFileResult upload(String bucketName, String objectName, InputStream stream, PutObjectOptions options) throws Exception {
        if (!this.bucketExists(bucketName)) {
            this.makeBucket(bucketName);
        }
        minioClient.putObject(bucketName, objectName, stream, options);
        // 返回生成文件名、访问路径
        return new StorageFileResult(objectName, minioClient.presignedGetObject(bucketName, objectName));
    }

    /**
     * @param file
     * @return java.lang.String
     * @Description 文件上传
     * @author exe.wangtaotao
     * @date 2020/10/21 13:45
     */
    public StorageFileResult upload(String bucketName, MultipartFile file,String directory) throws Exception {
        // bucket 不存在，创建
        if (!this.bucketExists(bucketName)) {
            this.makeBucket(bucketName);
        }
        InputStream inputStream = file.getInputStream();
        // PutObjectOptions，上传配置(文件大小，内存中文件分片大小)
        PutObjectOptions putObjectOptions = new PutObjectOptions(inputStream.available(), -1);
        // 文件的ContentType
        putObjectOptions.setContentType(file.getContentType());
        String fileName = minFileName(file.getOriginalFilename());
        minioClient.putObject(bucketName, fileName, inputStream, putObjectOptions);
        // 返回生成文件名、访问路径
        return new StorageFileResult(fileName, minioClient.presignedGetObject(bucketName, directory + fileName));
    }

    /**
     * @param response
     * @return java.lang.String
     * @Description 下载文件
     * @author exe.wangtaotao
     * @date 2020/10/21 15:18
     */
    public void download(HttpServletResponse response, String bucketName, String minFileName) throws Exception {
        InputStream fileInputStream = minioClient.getObject(bucketName, minFileName);
        response.setHeader("Content-Disposition", "attachment;filename=" + minFileName);
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(fileInputStream, response.getOutputStream());
    }

    /**
     * 删除一个文件
     *
     * @param bucketName
     * @param objectName
     */
    public boolean removeObject(String bucketName, String objectName)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.removeObject(bucketName, objectName);
            return true;
        }
        return false;
    }

    /**
     * @param bucketName
     * @param objectNames
     * @return java.util.List<java.lang.String>
     * @Description 删除指定桶的多个文件对象, 返回删除错误的对象列表，全部删除成功，返回空列表
     * @author exe.wangtaotao
     * @date 2020/10/21 16:43
     */
    public List<String> removeObject(String bucketName, List<String> objectNames)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        List<String> deleteErrorNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<DeleteError>> results = minioClient.removeObjects(bucketName, objectNames);
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                deleteErrorNames.add(error.objectName());
            }
        }
        return deleteErrorNames;
    }


    /**
     * @param originalFileName
     * @return java.lang.String
     * @Description 生成上传文件名
     * @author exe.wangtaotao
     * @date 2020/10/21 15:07
     */
    private String minFileName(String originalFileName) {
        String suffix = originalFileName;
        if (originalFileName.contains(SEPARATOR_DOT)) {
            suffix = originalFileName.substring(originalFileName.lastIndexOf(SEPARATOR_DOT));
        }
        return UUID.randomUUID().toString().replace(SEPARATOR_ACROSS, SEPARATOR_STR).toUpperCase() + suffix;
    }
}
