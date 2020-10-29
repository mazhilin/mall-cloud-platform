package com.mall.cloud.common.constant;

import javax.annotation.Nullable;

/**
 * <p>封装Qicloud项目ProductType类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-29 22:53
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum ProductType {
    /**
     * 系统-system
     */
    SYSTEM("system", "系统"),
    /**
     * 京东-jindong
     */
    JD("jindong", "京东"),
    /**
     * 京东-新富通-xinfutong
     */
    XFT("xinfutong", "京东-新富通"),
    /**
     * 网易-netease
     */
    NTES("netease", "网易"),
    /**
     * 天猫-tmall
     */
    TMALL("tmall", "天猫"),
    ;
    @Nullable
    private String code;
    @Nullable
    private String desc;

    ProductType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Nullable
    public String getCode() {
        return code;
    }

    public void setCode(@Nullable String code) {
        this.code = code;
    }

    @Nullable
    public String getDesc() {
        return desc;
    }

    public void setDesc(@Nullable String desc) {
        this.desc = desc;
    }
}
