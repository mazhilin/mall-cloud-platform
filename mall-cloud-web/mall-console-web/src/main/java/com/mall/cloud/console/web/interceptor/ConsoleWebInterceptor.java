package com.mall.cloud.console.web.interceptor;

import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.component.BaseHandlerInterceptor;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.utils.ApplicationServerUtil;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.service.UserServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>封装Qicloud项目ConsoleWebInterceptor类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-08 15:23
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class ConsoleWebInterceptor extends BaseHandlerInterceptor {
    private final List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501, 999);
    protected Logger logger = LoggerFactory.getLogger(ConsoleWebInterceptor.class);

    @DubboConsumerClient
    private UserServerService userServerService;

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
        String requestError = "/error";
        if (errorCodeList.contains(response.getStatus()) || requestError.equals(request.getServletPath())) {
            return false;
        }
        logger.info("---------------SpringBoot  Interceptor  begins to execute---------------");
        request = Objects.requireNonNull(ApplicationServerUtil.getRequest());
        String token = request.getHeader("token");
        if (CheckEmptyUtil.isEmpty(token)) {
            // 识别ajax的响应头
            String requestType = request.getHeader("X-Requested-With");
            // 如果是ajax类型，响应logout给前台
            if (CheckEmptyUtil.isNotEmpty(requestType) && "XMLHttpRequest".equals(requestType)) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print("logout");
                response.getWriter().close();
            } else {
                request.getRequestDispatcher("/api/console/home/toLogin").forward(request, response);
            }
            return false;
        } else {
            AdminUser adminUser = userServerService.validationAndRefreshToken(token);
            if (CheckEmptyUtil.isEmpty(adminUser)){
                //token验证失败
                request.getRequestDispatcher("/api/console/home/toLogin").forward(request, response);
                return false;
            }else {
                //token验证成功，请求中设置用户信息
                request.setAttribute(Constants.ADMIN_USER, adminUser);
            }
        }
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
        logger.info("---------------SpringBoot  Interceptor  end to execute---------------");
    }
}

