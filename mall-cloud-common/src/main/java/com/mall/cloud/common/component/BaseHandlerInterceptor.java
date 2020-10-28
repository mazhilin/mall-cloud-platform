package com.mall.cloud.common.component;

import lombok.Setter;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>封装Qicloud项目BaseHandlerInterceptor类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 09:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Setter
public abstract class BaseHandlerInterceptor implements AsyncHandlerInterceptor {
    /**
     * 配置拦截器-nextHandlerInterceptor
     */
    private BaseHandlerInterceptor[] interceptors;

    /**
     * 异步执行拦截器->afterConcurrentHandlingStarted( HttpServletRequest request, HttpServletResponse
     * response, Object handler) throws Exception
     *
     * @param request  request请求
     * @param response response响应
     * @param handler  handler拦截器
     * @throws Exception 异常信息
     */
    @Override
    public void afterConcurrentHandlingStarted(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Objects.nonNull(interceptors)) {
            for (BaseHandlerInterceptor interceptor : interceptors) {
                interceptor.afterConcurrentHandlingStarted(request, response, handler);
            }
        }
    }

    /**
     * 预处理回调方法->preHandle(HttpServletRequest request, HttpServletResponse response, Object
     * handler)throws Exception
     *
     * @param request  request请求
     * @param response response响应
     * @param handler  handler拦截器
     * @return 返回结果
     * @throws Exception 异常信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (Objects.nonNull(interceptors)) {
            return Boolean.TRUE;
        }
        for (BaseHandlerInterceptor interceptor : interceptors) {
            if (!interceptor.preHandle(request, response, handler)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 后处理回调方法->postHandle( HttpServletRequest request, HttpServletResponse response, Object
     * handler,ModelAndView modelAndView) throws Exception
     *
     * @param request      request请求
     * @param response     response响应
     * @param handler      handler拦截器
     * @param modelAndView modelAndView页面
     * @throws Exception 异常信息
     */
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {
        if (Objects.nonNull(interceptors)) {
            for (BaseHandlerInterceptor interceptor : interceptors) {
                interceptor.postHandle(request, response, handler, modelAndView);
            }
        }
    }

    /**
     * 整个请求处理完毕回调方法->afterCompletion( HttpServletRequest request, HttpServletResponse response, Object
     * handler, Exception exception) throws Exception
     *
     * @param request   request请求
     * @param response  response响应
     * @param handler   handler拦截器
     * @param exception exception异常信息
     * @throws Exception 异常信息
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        if (Objects.nonNull(interceptors)) {
            for (BaseHandlerInterceptor interceptor : interceptors) {
                interceptor.afterCompletion(request, response, handler, exception);
            }
        }
    }
}
