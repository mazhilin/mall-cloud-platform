package com.mall.cloud.passport.api.param;

import com.mall.cloud.common.persistence.param.BaseParam;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>封装Qicloud项目RequestMenuParam类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-20 22:28
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
public class RequestMenuParam extends BaseParam {
    private static final long serialVersionUID = -4535337979422942577L;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单类型[0-根目录 1-菜单 2-按钮]
     */
    private Integer type;
    /**
     * 菜单样式
     */
    private String style;
    /**
     * 父菜单id
     */
    private String parentId;
    /**
     * 菜单事件[依据菜单类型设置]
     */
    private String event;
    /**
     * 是否叶子节点[0-否 1-是]
     */
    private Integer isLeaf;
    /**
     * 菜单层级[0-一级菜单 1-二级菜单]
     */
    private Integer level;
    /**
     * 菜单作用域[1-后台 2-APP 3-SMR 4-公司管理员]
     */
    private Integer scope;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 菜单排序
     */
    private BigDecimal sort;
    /**
     * 是否左侧展示[0-否 1-是]
     */
    private Integer isShowMenu;
    /**
     * 是否内置菜单[0-否 1-是]
     */
    private Integer isInnerMenu;
    /**
     * 菜单权限类型[0-系统授权 1-公司授权 2-员工授权 3-公共授权]
     */
    private Integer menuAuth;
    /**
     * 是否路由缓冲[0-否 1-是]
     */
    private Integer keepAlive;
    /**
     * 当前登录用户Id
     */
    private String userId;
}
