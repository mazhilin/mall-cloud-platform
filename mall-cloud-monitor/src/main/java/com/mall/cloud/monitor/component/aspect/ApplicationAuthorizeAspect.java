package com.mall.cloud.monitor.component.aspect;

import com.mall.cloud.common.annotation.ApplicationAuthorize;
import com.mall.cloud.common.component.BaseApplicationAspect;
import com.mall.cloud.common.constant.PlatformType;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.utils.ApplicationServerUtil;
import com.mall.cloud.passport.api.authorize.AppApplicationAuthorize;
import com.mall.cloud.passport.api.authorize.SmrApplicationAuthorize;
import com.mall.cloud.passport.api.authorize.WebApplicationAuthorize;
import com.mall.cloud.passport.api.service.ListOperationsService;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.SetOperationsService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.apache.dubbo.config.annotation.Reference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>封装Qicloud项目ApplicationAuthorizeAspect类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 03:06
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Aspect
@Component
@Order(2)
public class ApplicationAuthorizeAspect implements BaseApplicationAspect, DisposableBean {
    @Reference
    protected ValueOperationsService<String, String> valueOperationsService;
    @Reference
    protected RedisOperationsService<String, String> redisOperationsService;
    @Reference
    protected ListOperationsService<String, String> listOperationsService;
    @Reference
    protected SetOperationsService<String, String> setOperationsService;
    @Reference
    private WebApplicationAuthorize webAuthorize;
    @Reference
    private AppApplicationAuthorize appAuthorize;
    @Reference
    private SmrApplicationAuthorize smrAuthorize;

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
     * @param point 切入点
     */
    @Override
    @Around(value = "serverPointcut()")
    public Object handlerAround(ProceedingJoinPoint point) throws Throwable {
        /**获取注解*/
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ApplicationAuthorize authorize = method.getAnnotation(ApplicationAuthorize.class);
        /** 获取注解属性*/
        boolean authorizeLogin = authorize.authorizeLogin();
        boolean authorizeResources = authorize.authorizeResources();
        PlatformType platform = authorize.platform();
        if (Objects.equals(PlatformType.WEB, platform)) {
            webAuthorize.getPlatform();
        } else if (Objects.equals(ScopeType.MOBILE, platform)) {
            appAuthorize.getPlatform();
        } else if (Objects.equals(ScopeType.WECHAT, platform)) {
            smrAuthorize.getPlatform();
        }

        ScopeType scope = authorize.scope();
        /**
         * 根据scope指定的端类型使用不同的token名从http请求头以及cookie中获取会话码
         */
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = null;
        String tokenName = null;

        if (Objects.equals(ScopeType.CONSOLE, scope)) {
            tokenName = Tokens.WEB_LOGIN_TOKEN;
        } else if (Objects.equals(ScopeType.MOBILE, scope)) {
            tokenName = Tokens.APP_LOGIN_TOKEN;
        } else if (Objects.equals(ScopeType.WECHAT, scope)) {
            tokenName = Tokens.SMR_LOGIN_TOKEN;
        }
        // 从cookie中获取token
        token = ApplicationServerUtil.getCookieVal(request, tokenName);
        // 从header中获取token
        if (Objects.isNull(token) && Objects.nonNull(request.getHeader(tokenName))) {
            token = request.getHeader(tokenName);
        }
        // 获取token参数
        if (Objects.isNull(token) && Objects.nonNull(request.getHeader(tokenName))) {
            token = request.getParameter(tokenName);
        }
        if (Objects.nonNull(scope) && authorizeLogin) {
            if (Objects.isNull(token)) {
                throw new ApplicationServerException("登录超时，请重新登录！");
            }
        }
        // 获取登录登录用户的ID
        String userId = null;
        String resourceKey = null;
        if (Objects.equals(ScopeType.CONSOLE, scope)) {
            userId = webAuthorize.getUserId(token);
            resourceKey = webAuthorize.getResourceKey(userId);
        } else if (Objects.equals(ScopeType.MOBILE, scope)) {
            userId = appAuthorize.getUserId(token);
            resourceKey = appAuthorize.getResourceKey(userId);
        } else if (Objects.equals(ScopeType.WECHAT, scope)) {
            userId = smrAuthorize.getUserId(token);
            resourceKey = smrAuthorize.getResourceKey(userId);
        }

        // 鉴权用户是否登录
        if (Objects.isNull(scope) && authorizeLogin) {
            // 获取不到用户ID则登录会话超时
            if (Objects.isNull(userId)) {
                throw new ApplicationServerException("登录超时，请重新登录！");
            } else {
                // 重新设置登录会话时长
                if (Objects.equals(ScopeType.CONSOLE, scope)) {
                    webAuthorize.setAuthorizeInfo(userId, token);
                } else if (Objects.equals(ScopeType.MOBILE, scope)) {
                    appAuthorize.setAuthorizeInfo(userId, token);
                } else if (Objects.equals(ScopeType.WECHAT, scope)) {
                    smrAuthorize.setAuthorizeInfo(userId, token);
                }
            }
        }

        // 鉴权url资源是否可调用
        if (Objects.isNull(scope) && authorizeResources) {
            String url = request.getRequestURI();
            url = url.replaceAll(request.getContextPath(), "");
            if (Objects.isNull(url)) {
                // 判断Url资源是否可调用
                if (!setOperationsService.isMember(resourceKey, url)) {
                    throw new ApplicationServerException("权限不足，请与系统管理员联系！");
                }
            }
        }

        return point.proceed();
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

    @Override
    public void destroy() throws Exception {

    }
}
