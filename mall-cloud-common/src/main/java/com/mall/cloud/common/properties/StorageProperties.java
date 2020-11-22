package com.mall.cloud.common.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
        private String endpoint=StringUtils.EMPTY;;

        /**
         * 默认的存储桶名称
         */
        private String bucketName = StringUtils.EMPTY;
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
         * 最大线程数，默认： 100
         */
        private Integer maxConnections = 100;
        /**
         * 对象存储服务的URL
         */
        private String endpoint=StringUtils.EMPTY;;

        /**
         * 默认的存储桶名称
         */
        private String bucketName = StringUtils.EMPTY;
    }
}
