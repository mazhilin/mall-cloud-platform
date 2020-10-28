package com.mall.cloud.common.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>封装Qicloud项目SwaggerProperties类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 09:46
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@ConfigurationProperties(prefix = "pivotal.cloud.swagger")
public class SwaggerProperties {
    /**
     * 是否开启swagger
     */
    private Boolean enabled;
    /**
     * swagger会解析的包路径
     */
    private String basePackage = StringUtils.EMPTY;
    /**
     * swagger会解析的url规则
     */
    private List<String> basePath = new ArrayList<>();
    /**
     * 在basePath基础上需要排除的url规则
     */
    private List<String> excludePath = new ArrayList<>();
    /**
     * 标题
     */
    private String title = StringUtils.EMPTY;
    /**
     * 描述
     */
    private String description = StringUtils.EMPTY;
    /**
     * 版本
     */
    private String version = StringUtils.EMPTY;
    /**
     * 许可证
     */
    private String license = StringUtils.EMPTY;
    /**
     * 许可证URL
     */
    private String licenseUrl = StringUtils.EMPTY;
    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = StringUtils.EMPTY;
    /**
     * host信息
     */
    private String host = StringUtils.EMPTY;
    /**
     * 联系人信息
     */
    private Contact contact = new Contact();
    /**
     * 全局统一鉴权配置
     */
    private Authorization authorization = new Authorization();

    @Data
    @NoArgsConstructor
    public static class Contact {
        /**
         * 联系人
         */
        private String name = StringUtils.EMPTY;
        /**
         * 联系人url
         */
        private String url = StringUtils.EMPTY;
        /**
         * 联系人email
         */
        private String email = StringUtils.EMPTY;
    }

    @Data
    @NoArgsConstructor
    public static class Authorization {
        /**
         * 鉴权策略ID，需要和SecurityReferences ID保持一致
         */
        private String name = StringUtils.EMPTY;
        /**
         * 需要开启鉴权URL的正则
         */
        private String authRegex = "^.*$";
        /**
         * 鉴权作用域列表
         */
        private List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        private List<String> tokenUrlList = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    public static class AuthorizationScope {
        /**
         * 作用域名称
         */
        private String scope = StringUtils.EMPTY;
        /**
         * 作用域描述
         */
        private String description = StringUtils.EMPTY;
    }
}
