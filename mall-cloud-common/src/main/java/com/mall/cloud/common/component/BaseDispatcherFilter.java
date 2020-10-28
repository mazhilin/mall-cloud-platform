package com.mall.cloud.common.component;

import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>封装Qicloud项目BaseDispatcherFilter类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 09:32
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Setter
@Component
@Configuration
public abstract class BaseDispatcherFilter implements Filter {
    private BaseDispatcherFilter[] filters;

    /**
     * 拦截具体操作-doFilter
     *
     * @param request  request
     * @param response response
     * @param chain    chain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (Objects.nonNull(filters)) {
            for (BaseDispatcherFilter dispatcherFilter : filters) {
                dispatcherFilter.doFilter(request, response, chain);
            }
        }
    }

    /**
     * 拦截配置初始化-init
     *
     * @param filterConfig filterConfig
     * @throws ServletException ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (Objects.nonNull(filters)) {
            for (BaseDispatcherFilter filter : filters) {
                filter.init(filterConfig);
            }
        }
    }

    /**
     * 拦截销毁操作-destroy
     */
    @Override
    public void destroy() {
        if (Objects.nonNull(filters)) {
            for (BaseDispatcherFilter filter : filters) {
                filter.destroy();
            }
        }
    }
}
