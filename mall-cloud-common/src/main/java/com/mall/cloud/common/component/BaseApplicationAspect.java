package com.mall.cloud.common.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p>封装Qicloud项目BaseApplicationAspect类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-28 09:37
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@FunctionalInterface
public interface BaseApplicationAspect {
    /**
     * 配置Aspect切面植入切点-serverPointcut
     */
    void serverPointcut();

    /**
     * Aspect切面前置通知,拦截记录用户操作
     *
     * @param joinPoint 切入点
     */
    default void handlerBefore(JoinPoint joinPoint) {

    }

    /**
     * Aspect切面环绕通知,拦截记录使用在方法aspect()上注册的切入点
     *
     * @param joinPoint 切入点
     */
    default Object handlerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }

    /**
     * Aspect切面后置通知,拦截记录用户操作
     *
     * @param joinPoint 切入点
     */
    default void handlerAfter(JoinPoint joinPoint) {

    }

    /**
     * Aspect切面后置返回通知,拦截记录使用在方法aspect()上注册的切入点
     *
     * @param joinPoint 切入点
     */
    default void handlerReturn(JoinPoint joinPoint) {

    }

    /**
     * Aspect切面异常通知,拦截记录异常信息和错误信息
     *
     * @param joinPoint 切点
     * @param exception 异常信息
     */
    default void handlerThrow(JoinPoint joinPoint, final Throwable exception) {

    }
}
