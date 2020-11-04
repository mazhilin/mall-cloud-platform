package com.mall.cloud.monitor.component.aspect;

import com.mall.cloud.common.component.BaseApplicationAspect;
import com.mall.cloud.console.api.param.ApplicationLoggerParam;
import com.mall.cloud.monitor.component.event.ApplicationLoggerEvent;
import com.mall.cloud.monitor.uitls.ApplicationLoggerUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>封装Qicloud项目ApplicationLoggerAspect类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 03:13
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Aspect
@Component
@Order(999)
@Lazy(value = true)
@AllArgsConstructor
public class ApplicationLoggerAspect implements BaseApplicationAspect {
    private final ApplicationEventPublisher publisher;

    /**
     * 配置Aspect切面植入切点-serverPointcut
     */
    @Override
    @Pointcut(value = "@annotation(com.mall.cloud.common.annotation.ApplicationLogger)")
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
     * @param point 切入点
     */
    @Override
    @Around(value = "serverPointcut()")
    public Object handlerAround(ProceedingJoinPoint point) throws Throwable {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        ApplicationLoggerParam param = ApplicationLoggerUtil.applicationLogger();
        param.setMethodName(strClassName + strMethodName);
        Object object = point.proceed();
        publisher.publishEvent(new ApplicationLoggerEvent(param));
        return object;
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
    @AfterThrowing(pointcut = "serverPointcut()", throwing = "exception")
    public void handlerThrow(JoinPoint joinPoint, Throwable exception) {

    }
}
