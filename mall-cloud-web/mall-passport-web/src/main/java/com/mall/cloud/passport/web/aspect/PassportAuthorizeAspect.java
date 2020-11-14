package com.mall.cloud.passport.web.aspect;

import com.mall.cloud.common.annotation.ApplicationAuthorize;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.component.BaseApplicationAspect;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.utils.ApplicationServerUtil;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.passport.api.service.*;
import org.apache.dubbo.config.annotation.Reference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>封装Qicloud项目PassportAuthorizeAspect类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-11 21:33
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Aspect
@Component
@Order(value = 2)
@Lazy
public class PassportAuthorizeAspect implements BaseApplicationAspect {

    private static Logger logger = LoggerFactory.getLogger(PassportAuthorizeAspect.class);
    @DubboConsumerClient
    private AdminAuthorizeService adminAuthorize;
    @DubboConsumerClient
    private AppAuthorizeService appAuthorize;
    @DubboConsumerClient
    private SmrAuthorizeService smrAuthorize;
    @Reference
    private ValueOperationsService<String, String> valueOperationsService;
    @Reference
    private SetOperationsService<String, String> setOperationsService;
    @Reference
    private RedisOperationsService<String, String> redisOperationsService;


    /**
     * 配置Aspect切面植入切点-serverPointcut
     */
    @Override
    @Pointcut(value = "@annotation(com.mall.cloud.common.annotation.ApplicationAuthorize)")
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
        /**
         * 获取注解
         */
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ApplicationAuthorize authorize = method.getAnnotation(ApplicationAuthorize.class);
        /**
         * 获取注解属性
         */
        boolean authorizeLogin = authorize.authorizeLogin();
        boolean authorizeResources = authorize.authorizeResources();
        ScopeType authorizeScope = authorize.authorizeScope();
        /**
         * 根据scope指定的端类型使用不同的token名从http请求头以及cookie中获取会话码
         */
        HttpServletRequest request = ApplicationServerUtil.getRequest();
        String token = null;
        String tokenName = null;
        if (ScopeType.WEB.equals(authorizeScope)) {
            tokenName = Tokens.WEB_LOGIN_TOKEN;
        } else if (ScopeType.APP.equals(authorizeScope)) {
            tokenName = Tokens.APP_LOGIN_TOKEN;
        } else if (ScopeType.SMR.equals(authorizeScope)) {
            tokenName = Tokens.SMR_LOGIN_TOKEN;
        }
        logger.info("token-key::{}", tokenName);

        // 从cookie中获取token
        token = ApplicationServerUtil.getCookieValue(request, tokenName);
        logger.info("token-cookie-value::{}", token);
        // 从header中获取token
        if (CheckEmptyUtil.isEmpty(token) && CheckEmptyUtil.isNotEmpty(request.getHeader(tokenName))) {
            token = request.getHeader(tokenName);
        }
        logger.info("token-header-value::{}", token);
        // 获取参数token
        if (CheckEmptyUtil.isEmpty(token) && CheckEmptyUtil.isNotEmpty(request.getParameter(tokenName))) {
            token = request.getParameter(tokenName);
        }
        logger.info("token-parameter-value::{}", token);
        if (CheckEmptyUtil.isNotEmpty(authorize) && authorizeLogin) {
            if (CheckEmptyUtil.isEmpty(token)) {
                throw new PassportServerException("登录超时，请重新登录！");
            }
        }
        // 获取登录登录用户的ID
        String userId = null;
        String resourceKey = null;


        if (ScopeType.WEB.equals(authorizeScope)) {
            userId = adminAuthorize.getUserId(token);
            resourceKey = adminAuthorize.getResources(userId);
        } else if (ScopeType.APP.equals(authorizeScope)) {
            userId = appAuthorize.getUserId(token);
            resourceKey = appAuthorize.getResources(userId);
        } else if (ScopeType.SMR.equals(authorizeScope)) {
            userId = smrAuthorize.getUserId(token);
            resourceKey = smrAuthorize.getResources(userId);
        }

        // 鉴权用户是否登录
        if (CheckEmptyUtil.isNotEmpty(authorize) && authorizeLogin) {
            // 获取不到用户ID则登录会话超时
            if (CheckEmptyUtil.isEmpty(userId)) {
                throw new PassportServerException("登录超时，请重新登录！");
            } else {
                // 重新设置登录会话时长
                if (ScopeType.WEB.equals(authorizeScope)) {
                    adminAuthorize.setAuthorize(userId, token);
                } else if (ScopeType.APP.equals(authorizeScope)) {
                    appAuthorize.setAuthorize(userId, token);
                } else if (ScopeType.SMR.equals(authorizeScope)) {
                    smrAuthorize.setAuthorize(userId, token);
                }
            }
        }
        // 鉴权url资源是否可调用
        if (CheckEmptyUtil.isNotEmpty(authorize) && authorizeResources) {
            String url = request.getRequestURI();
            url = url.replaceAll(request.getContextPath(), "");
            if (CheckEmptyUtil.isNotEmpty(url)) {
                // 判断Url资源是否可调用
                if (!setOperationsService.isMember(resourceKey, url)) {
                    logger.error(String.format("权限不足，%s", url));
                    throw new PassportServerException("权限不足，请与系统管理员联系！");
                }
            }
        }
        // 设置值传递到下个AOP（如系统日志）
        // 执行方法体
        Object object = joinPoint.proceed();
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
    @AfterThrowing(value = "serverPointcut()", throwing = "exception")
    public void handlerThrow(JoinPoint joinPoint, Throwable exception) {

    }
}
