package com.mall.cloud.common.constant;

import javax.annotation.Nullable;

/**
 * <p>封装Qicloud项目OperationType类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 10:00
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public enum OperationType {
    /**
     * 新增-add
     */
    ADD("add", "新增"),
    /**
     * 编辑-edit
     */
    EDIT("edit", "编辑"),
    /**
     * 插入-insert
     */
    INSERT("insert", "插入"),
    /**
     * 删除-delete
     */
    DELETE("delete", "删除"),
    /**
     * 修改-update
     */
    UPDATE("update", "修改"),
    /**
     * 查询-query
     */
    QUERY("query", "查询"),
    /**
     * 导入-import
     */
    IMPORT("import", "导入"),
    /**
     * 导出-export
     */
    EXPORT("export", "导出"),
    /**
     * 启用-enable
     */
    ENABLE("enable", "启用"),
    /**
     * 禁用-disable
     */
    DISABLE("disable", "禁用"),
    /**
     * 上线-online
     */
    ONLINE("online", "上线"),
    /**
     * 下线-offline
     */
    OFFLINE("offline", "下线"),
    /**
     * 预发布-release
     */
    RELEASE("release", "预发布"),
    /**
     * 发布-publish
     */
    PUBLISH("publish", "发布"),
    /**
     * 取消-cancel
     */
    CANCEL("cancel", "取消"),
    /**
     * 恢复-restore
     */
    RESTORE("restore", "恢复"),
    /**
     * 排序-sort
     */
    SORT("sort", "排序"),
    /**
     * 预览-preview
     */
    PREVIEW("preview", "预览"),
    /**
     * 分享-share
     */
    SHARE("share", "分享"),
    /**
     * 复制-copy
     */
    COPY("copy", "复制"),
    /**
     * 转移-transfer
     */
    TRANSFER("transfer", "转移"),
    /**
     * 置顶-stick
     */
    STICK("stick", "置顶"),
    /**
     * 点赞-like
     */
    LIKE("like", "点赞"),
    /**
     * 浏览-browse
     */
    BROWSE("browse", "浏览"),
    /**
     * 请求-request
     */
    REQUEST("request", "请求"),
    /**
     * 收藏-collect
     */
    COLLECT("collect", "收藏"),
    /**
     * 评论-comment
     */
    COMMENT("comment", "评论"),
    /**
     * 上架-push
     */
    PUSH("push", "上架"),
    /**
     * 下架-pull
     */
    PULL("pull", "下架"),
    ;
    @Nullable
    private String code;
    @Nullable
    private String desc;

    OperationType(String code, String desc) {
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
