package com.mall.cloud.passport.web.controller.common;

import com.google.common.collect.Lists;
import com.mall.cloud.common.annotation.ApplicationAuthorize;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.component.authorize.ApplicationLoginAuthorize;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.ResponseType;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.constant.Tokens;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.persistence.controller.Controller;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.service.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>封装Qicloud项目ConsoleLoginController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-07 11:52
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping(value = "/api/console/center", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
public class ConsoleCenterController extends ApplicationLoginAuthorize implements Controller {

    @DubboConsumerClient
    private LoginServerService loginServerService;
    @DubboConsumerClient
    private UserServerService userServerService;
    @DubboConsumerClient
    private RedisOperationsService<String, Object> redisOperationsService;
    @DubboConsumerClient
    private ValueOperationsService<String, Object> valueOperationsService;
    @DubboConsumerClient
    private AdminAuthorizeService adminAuthorize;


    /**
     * 系统后台登录方法-login
     *
     * @param account    登录账户[系统用户,APP用户,员工用户]
     * @param password   登录密码
     * @param auto       是否自动登录[0-否 1-是]
     * @return 返回结果
     * @throws ApplicationServerException 应用服务异常
     */
    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public String login(
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "auto", required = false) Integer auto)
            throws ApplicationServerException {
        ResponseResult result = new ResponseResult();
        // [1].校验用户请求参数
        if (CheckEmptyUtil.isEmpty(account)) {
            result.setError(ResponseType.EMPTY.code(), "登录账号不能为空!");
            return result.parseToJson(result);
        }
        if (CheckEmptyUtil.isEmpty(password)) {
            result.setError(ResponseType.EMPTY.code(), "登录密码不能为空!");
            return result.parseToJson(result);
        }
        // [2].查询用户数据是否正确
        AdminUser adminUser = loginServerService.queryAdminUser(account, password);
        if (CheckEmptyUtil.isEmpty(adminUser)) {
            result.setError("登录失败!用户名/密码错误!");
            return result.parseToJson(result);
        }
        if (CheckEmptyUtil.isNotEmpty(adminUser.getStatus())
                && Objects.equals(Constants.DISABLE, adminUser.getStatus())) {
            result.setError("用户已被锁定，请联系管理员!");
            return result.parseToJson(result);
        }
        // [3] 更新用户登录时间
        adminUser.setLoginTime(LocalDateTime.now());
        // TODO...
        try {
            List<String> resourceList = Lists.newLinkedList();
            String token = login(adminUser.getId(), resourceList, adminAuthorize);
            result.putResult(Tokens.WEB_LOGIN_TOKEN, token);
        } catch (ApplicationServerException exception) {
            logger.error("用户登陆失败，账号:{},密码：{},TRACE:e", account, password, exception);
            result.setError("系统繁忙，请稍后再试!");
        }
        return result.parseToJson(result);
    }

    /**
     * 推出登陆
     * @return 结果
     */
    @ApplicationAuthorize(authorizeResources = false, scope = ScopeType.WEB)
    @PostMapping(value = "/logout", produces = "application/json;charset=UTF-8")
    public String logout(
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "auto", required = false) Integer auto)
            throws ApplicationServerException {
        ResponseResult result = new ResponseResult();
        try {
            result = loginServerService.login(result, account, password);
            if (Objects.equals(auto, 1)) {
            }
        } catch (ApplicationServerException exception) {
            logger.error("用户登陆失败，账号:{},密码：{},TRACE:e", account, password, exception);
            result.setError("系统繁忙，请稍后再试!");
        }
        return result.parseToJson(result);
    }


    /**
     * 后台用户修改密码
     *
     * @param password        新密码
     * @param confirmPassword 确认密码
     * @return 返回结果
     * @throws ApplicationServerException
     */
    @ApplicationAuthorize(authorizeResources = false, scope = ScopeType.WEB)
    @PostMapping(value = "/updatePassword", produces = "application/json;charset=UTF-8")
    public String updatePassword(
            @RequestParam(value = "password") String password,
            @RequestParam(value = "confirmPassword") String confirmPassword)
            throws ApplicationServerException {
        ResponseResult result = new ResponseResult();
        return result.parseToJson(result);
    }


}
