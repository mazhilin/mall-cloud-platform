package com.mall.cloud.common.constant;

import javax.annotation.Nullable;

/**
 * <p>封装Qicloud项目PlatformType类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 09:58
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum PlatformType {
    /**
     * Web平台-web
     */
    WEB("web", "Web平台"),
    /**
     * APP平台-app[mobile-移动端APP]
     */
    APP("app", "APP平台"),
    /**
     * SMR平台-smr[small routine-小程序]
     */
    SMR("smr", "SMR平台"),
    ;

    @Nullable
    private String code;
    @Nullable
    private String desc;

    PlatformType(@Nullable String code, @Nullable String desc) {
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
