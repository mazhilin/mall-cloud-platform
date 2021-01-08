package com.mall.cloud.common.utils;

import com.aliyun.oss.model.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.util.Comparator.comparingInt;

/**
 * <p>封装Qicloud项目StorageFileMultiparts类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-24 22:52
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class StorageFileMultiparts implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(StorageFileMultiparts.class);
    private MultipartFile multipartFile;
    private long startPos;
    private long partSize;
    private int partNumber;
    private String uploadId;
    private static String objectName;
    private static String bucketName;
    private CountDownLatch countLatch;

    // 新建一个List保存每个分块上传后的ETag和PartNumber
    protected static List<PartETag> partETags = Collections.synchronizedList(Lists.newLinkedList());

    /**
     * 创建构造方法
     *
     * @param multipartFile 要上传的文件
     * @param startPos      每个文件块的开始
     * @param partSize      分片大小
     * @param partNumber    分片个数
     * @param uploadId      作为块的标识
     * @param objectName    上传到OSS后的文件名
     */
    public StorageFileMultiparts(MultipartFile multipartFile, long startPos, long partSize, int partNumber, String uploadId, String objectName, String bucketName, CountDownLatch countLatch) {
        this.multipartFile = multipartFile;
        this.startPos = startPos;
        this.partSize = partSize;
        this.partNumber = partNumber;
        this.uploadId = uploadId;
        this.countLatch = countLatch;
        StorageFileMultiparts.objectName = objectName;
        StorageFileMultiparts.bucketName = bucketName;

    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            // 获取文件流
            inputStream = multipartFile.getInputStream();
            // 跳到每个分块的开头
            inputStream.skip(this.startPos);
            // 创建UploadPartRequest，上传分块
            UploadPartRequest request = new UploadPartRequest();
            request.setBucketName(bucketName);
            request.setKey(objectName);
            request.setUploadId(this.uploadId);
            request.setInputStream(inputStream);
            request.setPartSize(this.partSize);
            request.setPartNumber(this.partNumber);
            UploadPartResult result = StorageServerUtil.ossClient.uploadPart(request);
            logger.info("阿里云OSS服务：#aliyun-oss-partNumber={}", this.partNumber);
            synchronized (Collections.synchronizedList(partETags)) {
                // 将返回的PartETag保存到List中。
                partETags.add(result.getPartETag());
                logger.info("阿里云OSS服务：#aliyun-oss-partSize={}", partETags.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭文件流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            countLatch.countDown();
        }
    }

    /**
     * 初始化分块上传事件并生成uploadID，用来作为区分分块上传事件的唯一标识
     *
     * @param bucketName bucketName名称
     * @param key        ObjectName
     * @return
     */
    protected static String productUploadId(String bucketName, String key) {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
        request.addHeader("Cache-Control", "no-cache");
        InitiateMultipartUploadResult result = StorageServerUtil.ossClient.initiateMultipartUpload(request);
        return result.getUploadId();
    }

    /**
     * 将文件分块进行升序排序并执行文件上传
     */
    protected static void completeMultipartUpload(String uploadId) {
        // 将文件分块按照升序排序
        partETags.sort(comparingInt(PartETag::getPartNumber));
        CompleteMultipartUploadRequest uploadRequest = new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);
        // 完成分块上传
        StorageServerUtil.ossClient.completeMultipartUpload(uploadRequest);
        StorageServerUtil.ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    /**
     * 列出文件所有分块的清单
     *
     * @param uploadId
     */
    protected static void listAllParts(String uploadId) {
        ListPartsRequest listParts = new ListPartsRequest(bucketName, objectName, uploadId);
        // 获取上传的所有分块信息
        PartListing partListing = StorageServerUtil.ossClient.listParts(listParts);
        // 获取分块的大小
        int partCount = partListing.getParts().size();
        // 遍历所有分块
        for (int i = 0; i < partCount; i++) {
            PartSummary partSummary = partListing.getParts().get(i);
            logger.info("阿里云OSS服务分块描述: #aliyun-oss-partsFileNo={},#aliyun-oss-partsFileEtag={},#aliyun-oss-partsFileSize={}", partSummary.getPartNumber(), partSummary.getETag(), partSummary.getSize());
        }
    }
}
