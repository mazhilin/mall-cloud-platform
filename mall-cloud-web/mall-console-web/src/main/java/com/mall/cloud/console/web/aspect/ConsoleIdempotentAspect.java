package com.mall.cloud.console.web.aspect;

import com.mall.cloud.common.component.BaseApplicationAspect;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.utils.ApplicationServerUtil;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.apache.dubbo.config.annotation.Reference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>封装Qicloud项目ConsoleIdempotentAspect类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-13 13:49
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Aspect
@Component
@Order(value = 2)
@Lazy
public class ConsoleIdempotentAspect implements BaseApplicationAspect {
    private final String POST_METHOD_PATH = "post_method_path";
    @Reference
    protected RedisOperationsService<String, String> redisOperationsService;
    @Reference
    protected ValueOperationsService<String, String> valueOperationsService;

    /**
     * 配置Aspect切面植入切点-serverPointcut
     */
    @Override
    @Pointcut(value = "@annotation(com.mall.cloud.common.annotation.ApplicationIdempotent)")
    public void serverPointcut() {

    }

    /**
     * Aspect切面前置通知,拦截记录用户操作
     *
     * @param joinPoint 切入点
     */
    @Override
    @Before(value = "serverPointcut()")
    public void handlerBefore(JoinPoint joinPoint) {

    }

    /**
     * Aspect切面环绕通知,拦截记录使用在方法aspect()上注册的切入点
     *
     * @param joinPoint 切入点
     */
    @Override
    @Around(value = "serverPointcut()")
    public Object handlerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        //取到当前请求路径和用户信息token 缓存在redis里面
        String loginToken = ApplicationServerUtil.getCookieValue(request, Tokens.APP_LOGIN_TOKEN);
        if (CheckEmptyUtil.isNotEmpty(loginToken)) {
            if (CheckEmptyUtil.isNotEmpty(valueOperationsService.get(Objects.requireNonNull(request).getRequestURI() + loginToken))) {
                throw new ApplicationServerException("表单正在提交......");
            }
            valueOperationsService.set(request.getRequestURI() + loginToken, POST_METHOD_PATH, 3, TimeUnit.SECONDS);
        }
        return result;
    }

    /**
     * Aspect切面后置通知,拦截记录用户操作
     *
     * @param joinPoint 切入点
     */
    @Override
    @After(value = "serverPointcut()")
    public void handlerAfter(JoinPoint joinPoint) {

    }

    /**
     * Aspect切面后置返回通知,拦截记录使用在方法aspect()上注册的切入点
     *
     * @param joinPoint 切入点
     */
    @Override
    @AfterReturning(value = "serverPointcut()")
    public void handlerReturn(JoinPoint joinPoint) {

    }

    /**
     * Aspect切面异常通知,拦截记录异常信息和错误信息
     *
     * @param joinPoint 切点
     * @param exception 异常信息
     */
    @Override
    @AfterThrowing(value = "serverPointcut()", throwing = "exception")
    public void handlerThrow(JoinPoint joinPoint, Throwable exception) {

    }
}
