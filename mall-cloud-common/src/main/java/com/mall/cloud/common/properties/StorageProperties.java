package com.mall.cloud.common.properties;

import com.aliyun.oss.OSSClient;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>封装Qicloud项目StorageProperties类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-20 01:37
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@ConfigurationProperties(prefix = "pivotal.cloud.storage")
public class StorageProperties {
    /**
     * 是否开启
     */
    private Boolean enabled;

    private String environment;

    private Minio minio;

    private Alioss alioss;

    @Bean
    public MinioClient minioClient() throws InvalidEndpointException, InvalidPortException {
        MinioClient minioClient = new MinioClient(getMinio().getEndpoint(), getMinio().getAccessKeyId(), getMinio().getAccessKeySecret());
        return minioClient;
    }

    @Bean
    public OSSClient ossClient() throws InvalidEndpointException, InvalidPortException {
        OSSClient ossClient = new OSSClient(getAlioss().getEndpoint(), getAlioss().getAccessKeyId(), getAlioss().getAccessKeySecret());
        return ossClient;
    }

    @Data
    @NoArgsConstructor
    public static class Minio {
        /**
         * Access key就像用户ID，可以唯一标识你的账户
         */
        private String accessKeyId;
        /**
         * Secret key是你账户的密码
         */
        private String accessKeySecret;
        /**
         * 最大线程数，默认： 100
         */
        private Integer maxConnections = 100;
        /**
         * 对象存储服务的URL
         */
        private String endpoint = "default";

        /**
         * 默认的存储桶名称
         */
        private String bucketName = "default";
    }

    @Data
    @NoArgsConstructor
    public static class Alioss {
        /**
         * Access key就像用户ID，可以唯一标识你的账户
         */
        private String accessKeyId;
        /**
         * Secret key是你账户的密码
         */
        private String accessKeySecret;
        /**
         * 对象存储服务的URL
         */
        private String endpoint = "default";

        /**
         * 默认的存储桶名称
         */
        private String bucketName = "default";
    }

}
