package com.mall.cloud.common.persistence.result;

import lombok.Data;

/**
 * <p>封装Qicloud项目ZtreeNode类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-25 09:55
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
public class ZtreeNode implements Result {
    private static final long serialVersionUID = 2924827202772410226L;
    private Long id;
    private Long pId;
    private String name;
    private boolean open;
    private boolean checked;
    private boolean nocheck;
}
