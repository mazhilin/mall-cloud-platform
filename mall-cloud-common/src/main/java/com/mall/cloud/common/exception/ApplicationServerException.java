package com.mall.cloud.common.exception;

import lombok.NoArgsConstructor;

/**
 * <p>封装Qicloud项目ApplicationServerException类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 10:47
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@NoArgsConstructor
public class ApplicationServerException extends RuntimeException{


    private static final long serialVersionUID = 5397857126588548097L;

    public ApplicationServerException(String message) {
        super(message);
    }

    public ApplicationServerException(Throwable cause) {
        super(cause);
    }

    public ApplicationServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
