package com.mall.cloud.common.component.interceptor;

import com.mall.cloud.common.component.BaseHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>封装Qicloud项目GolbalApplictaionInterceptor类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 20:28
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class GolbalApplictaionInterceptor extends BaseHandlerInterceptor {
    /**
     * 配置拦截器-nextHandlerInterceptor
     *
     * @param interceptors
     */
    @Override
    public void setInterceptors(BaseHandlerInterceptor[] interceptors) {
        super.setInterceptors(interceptors);
    }

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
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //暂时开启跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");
        return true;
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
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
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
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        super.afterCompletion(request, response, handler, exception);
    }
}
