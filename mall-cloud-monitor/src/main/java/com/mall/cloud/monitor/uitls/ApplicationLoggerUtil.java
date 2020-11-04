package com.mall.cloud.monitor.uitls;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.mall.cloud.monitor.api.param.ApplicationLoggerParam;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>封装Qicloud项目ApplicationLoggerUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 21:34
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@UtilityClass
public class ApplicationLoggerUtil {
    /**
     * 系统操作日志记录
     *
     * @return
     */
    public ApplicationLoggerParam applicationLogger() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        ApplicationLoggerParam param = new ApplicationLoggerParam();
        param.setIpAddress(ServletUtil.getClientIP(request));
        param.setApiAddress(URLUtil.getPath(request.getRequestURI()));
        param.setRequestType(request.getMethod());
        param.setInputParam(HttpUtil.toParams(request.getParameterMap()));
        param.setSystemVersion(request.getHeader("user-agent"));
        param.setCreateBy("");
        param.setCreateTime(LocalDateTime.now());
        param.setUpdateBy("");
        param.setUpdateTime(LocalDateTime.now());
        return param;
    }
}
