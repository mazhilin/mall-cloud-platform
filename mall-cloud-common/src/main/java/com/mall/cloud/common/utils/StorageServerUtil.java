package com.mall.cloud.common.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.Comparator.comparingInt;

/**
 * <p>封装Qicloud项目StorageServerUtil类.<br></p>
 * <p>阿里云文件上传服务工具类<br></p>
 *
 * @author Powered by marklin 2020-11-19 10:11
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class StorageServerUtil {
    private static Logger logger = LoggerFactory.getLogger(StorageFileMultiparts.class);
    /**
     * 阿里云API的密钥Access Key ID
     */
    public static String accessKeyId = "LTAI1dCL1uM8U4Yu";
    /**
     * 阿里云API的密钥Access Key Secret
     */
    public static String accessKeySecret = "6Sp1hCJR5W6bm9Zy8mwJBzdoaUtQPL";
    /**
     * 阿里云API的外网域名
     */
    public static String endpoint = "http://oss-cn-qingdao.aliyuncs.com";
    /**
     * 阿里云API的内网域名
     */
    public static String ecsEndpoint = "http://oss-cn-qingdao-internal.aliyuncs.com";
    public static String ossEndPoint = "http://bucket.img-cn-shenzhen.aliyuncs.com";

    public static OSSClient ossClient = null;
    /**
     * OSS上传MultipartFile文件
     *
     * @param uploadFile 文件
     * @param bucketName 存储空间
     * @return 文件URL
     * @throws Exception 异常
     */
    public static String upload(MultipartFile uploadFile, String bucketName, Boolean environment) throws Exception {
        // 创建OSSClient实例
        if (Boolean.TRUE.equals(environment)) {
            ossClient = new OSSClient(ecsEndpoint, accessKeyId, accessKeySecret);
        } else {
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        }
        //获取文件路径
        String fileName = GenerateCodeUtil.produceCode(4) + StringUtils.EMPTY + uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String objectName = getFileName(fileName);
        ossClient.putObject(bucketName, objectName, uploadFile.getInputStream());
        return generatePresignedUrl(ossClient, bucketName, objectName);
    }

    /**
     * 分片上传实现文件
     *
     * @param uploadFile 文件
     * @param bucketName 存储空间
     * @return 文件URL
     * @throws Exception 异常信息
     * @author Marklin
     */
    public static String uploadMultipart(MultipartFile uploadFile, String bucketName, Boolean environment) throws Exception {
        // 创建OSSClient实例
        ClientConfiguration configuration = new ClientConfiguration();
        /** 连接空闲超时时间，超时则关闭*/
        configuration.setIdleConnectionTime(360000L);
        if (Boolean.TRUE.equals(environment)) {
            ossClient = new OSSClient(ecsEndpoint, accessKeyId, accessKeySecret, configuration);
        } else {
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, configuration);
        }
        String objectName = getFileName(uploadFile.getOriginalFilename());
        /**
         * 初始化一个分片上传事件
         */
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);
        InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = result.getUploadId();
        LoggerServerUtil.info(logger, "阿里云OSS文件分片：#aliyun-oss-uploadId={}", () -> uploadId);
        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags = Lists.newLinkedList();
        // 计算文件有多少个分片。
        final long partSize = 128 * 1024 * 1024L;
        long fileLength = uploadFile.getSize();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        CountDownLatch countLatch = new CountDownLatch(partCount);
        LoggerServerUtil.info(logger, "阿里云OSS服务分片统计个数：#aliyun-oss-countLatch={}", countLatch::getCount);
        // 遍历分片上传。
        for (int i = 0; i < partCount; i++) {
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream inputStream = uploadFile.getInputStream();
            // 跳过已经上传的分片。
            inputStream.skip(startPos);
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(objectName);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(inputStream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber(i + 1);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果会包含一个PartETag。PartETag将被保存到partETags中。
            partETags.add(uploadPartResult.getPartETag());
        }
        countLatch.await();
        /**
         * partETags(上传块的ETag与块编号（PartNumber）的组合) 如果校验与之前计算的分块大小不同，则抛出异常
         */
        int partETagsSize = partETags.size();
        logger.info("阿里云OSS服务分块校验对比:#aliyun-oss-partETagsSize={},#aliyun-oss-partCount={}", partETagsSize, partCount);
        if (partETagsSize != partCount) {
            throw new IllegalStateException("阿里云OSS服务分块大小与原始文件所计算的分块大小不一致!");
        } else {
            logger.info("阿里云OSS服务上传文件名:#aliyun-oss-objectName={}", objectName);
        }
        // 将文件分块按照升序排序
        partETags.sort(comparingInt(PartETag::getPartNumber));
        CompleteMultipartUploadRequest uploadRequest = new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);
        // 完成分块上传
        ossClient.completeMultipartUpload(uploadRequest);
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        // 上传
        long time = System.currentTimeMillis();
        logger.info("阿里云OSS服务上传成功: #aliyun-oss-url={}", getUrl(ossClient, time, bucketName, objectName));
        return getUrl(ossClient, time, bucketName, objectName);
    }


    /**
     * 分片上传实现文件
     *
     * @param uploadFile 文件
     * @param bucketName 存储空间
     * @return 文件URL
     * @throws Exception 异常信息
     * @author Marklin
     */
    public static String uploadMultiparts(MultipartFile uploadFile, String bucketName, Boolean environment) throws Exception {
        String uploadUrl = StringUtils.EMPTY;
        ClientConfiguration configuration = new ClientConfiguration();
        /** 连接空闲超时时间，超时则关闭*/
        configuration.setIdleConnectionTime(360000L);
        //创建OSSClient实例
        if (Boolean.TRUE.equals(environment)) {
            LoggerServerUtil.info(logger, "内网上传{}", () -> environment);
            ossClient = new OSSClient(ecsEndpoint, accessKeyId, accessKeySecret, configuration);
        } else {
            LoggerServerUtil.info(logger, "外网上传{}", () -> environment);
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, configuration);
        }
        String fileName = GenerateCodeUtil.produceCode(4) + StringUtils.EMPTY + uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String objectName = getFileName(fileName);
        /**1.初始化一个分片上传事件**/
        String uploadId = StorageFileMultiparts.productUploadId(bucketName, objectName);
        LoggerServerUtil.info(logger, "阿里云OSS文件分片：#aliyun-oss-uploadId={}", () -> uploadId);
        try {
            /**2.上传分片**/
            long partSize = 8 * 1024 * 1024L;
            long fileSize = uploadFile.getSize();
            LoggerServerUtil.info(logger, "阿里云OSS服务分片上传文件大小：#aliyun-oss-fileSize={}", () -> fileSize);
            // 计算分块数目
            int partCount = (int) (fileSize / partSize);
            if (fileSize % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                throw new IllegalStateException("Total parts count should not exceed 10000!");
            } else {
                int finalPartCount = partCount;
                LoggerServerUtil.info(logger, "阿里云OSS服务分片上传总数：#aliyun-oss-partCount={}", () -> finalPartCount);
            }
            //遍历分片上传
            /**
             * 将分好的文件块加入到list集合中
             */
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            CountDownLatch countLatch = new CountDownLatch(partCount);
            LoggerServerUtil.info(logger, "阿里云OSS服务分片统计个数：#aliyun-oss-countLatch={}", countLatch::getCount);
            for (int index = 0; index < partCount; index++) {
                // 起始point
                long startPos = index * partSize;
                // 判断当前partSize的长度 是否最后一块
                long curPartSize = (index + 1 == partCount) ? (fileSize - startPos) : partSize;
                // 线程执行。将分好的文件块加入到list集合中()
                if (!executorService.isShutdown()) {
                    executorService.execute(new StorageFileMultiparts(uploadFile, startPos, curPartSize, index + 1, uploadId, objectName, bucketName, countLatch));
                }
            }
            countLatch.await();
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                try {
                    // 用于等待子线程结束，再继续执行下面的代码
                    executorService.awaitTermination(5, TimeUnit.MILLISECONDS);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            /**
             * partETags(上传块的ETag与块编号（PartNumber）的组合) 如果校验与之前计算的分块大小不同，则抛出异常
             */
            int partETagsSize = StorageFileMultiparts.partETags.size();
            logger.info("阿里云OSS服务分块校验对比:#aliyun-oss-partETagsSize={},#aliyun-oss-partCount={}", partETagsSize, partCount);
            if (partETagsSize != partCount) {
                throw new IllegalStateException("阿里云OSS服务分块大小与原始文件所计算的分块大小不一致!");
            } else {
                logger.info("阿里云OSS服务上传文件名:#aliyun-oss-objectName={}", objectName);
            }
            /*
             * 列出文件所有的分块清单并打印到日志中，该方法仅仅作为输出使用
             */
            StorageFileMultiparts.listAllParts(uploadId);
            /**3.完成分片上传**/
            StorageFileMultiparts.completeMultipartUpload(uploadId);
            uploadUrl = "https://" + bucketName + "." + "oss-cn-qingdao.aliyuncs.com" + "/" + ossClient.getObject(bucketName, objectName).getKey().trim();
            logger.info("阿里云OSS服务上传成功: #aliyun-oss-url={}", uploadUrl);
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
        } finally {
            StorageFileMultiparts.partETags.clear();
            StorageFileMultiparts.partETags = Collections.synchronizedList(Lists.newLinkedList());
            if (ossClient != null) {
                //关闭OSSClient
                ossClient.shutdown();
            }
        }
        return uploadUrl;
    }

    /**
     * OSS上传数据流文件
     *
     * @param content          数据流
     * @param bucketName       存储空间
     * @param originalFilename 文件名称
     * @return 文件URL
     * @throws Exception 异常
     */
    public static String uploadBytes(byte[] content, String bucketName, String originalFilename, Boolean environment) throws Exception {
        // 创建OSSClient实例
        if (Boolean.TRUE.equals(environment)) {
            ossClient = new OSSClient(ecsEndpoint, accessKeyId, accessKeySecret);
        } else {
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        }
        //获取文件路径
        //获取文件路径
        String fileName = GenerateCodeUtil.produceCode(4) + StringUtils.EMPTY + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = getFileName(fileName);
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        return generatePresignedUrl(ossClient, bucketName, objectName);
    }

    /**
     * 获取文件存储路径
     *
     * @param originalFilename 文件名称
     * @return 路径
     */
    private static String getFileName(String originalFilename) {
        Calendar cal = Calendar.getInstance();
        StringBuilder prePath = new StringBuilder();
        cal.setTimeInMillis(System.currentTimeMillis());
        prePath.append(cal.get(Calendar.YEAR)).append("/")
                .append(cal.get(Calendar.MONTH) + 1).append("/")
                .append(cal.get(Calendar.DAY_OF_MONTH)).append("/")
                .append(originalFilename);
        return prePath.toString();
    }

    /**
     * 上传文件结束后，获取文件地址
     *
     * @param ossClient  OSSClient实例
     * @param time       上传时间
     * @param bucketName 存储空间
     * @param fileName   文件名
     * @return url
     */
    private static String getUrl(OSSClient ossClient, long time, String bucketName, String fileName) {
        //关闭client
        ossClient.shutdown();
        //获取文件URL
        Date expiration = new Date(time + 3600L * 1000 * 24 * 365 * 10);
        return ossClient.generatePresignedUrl(bucketName, fileName, expiration).toString();
    }

    /**
     * 生成文件访问链接
     *
     * @param ossClient  OSSClient实例
     * @param bucketName 存储空间
     * @param objectName 文件名
     * @return url
     */
    private static String generatePresignedUrl(OSSClient ossClient, String bucketName, String objectName) {
        String uploadUrl = StringUtils.EMPTY;
        if (ossClient != null) {
            uploadUrl = "https://" + bucketName + "." + "oss-cn-qingdao.aliyuncs.com" + "/" + ossClient.getObject(bucketName, objectName).getKey().trim();
            ossClient.shutdown();
        }
        return uploadUrl;
    }
}
