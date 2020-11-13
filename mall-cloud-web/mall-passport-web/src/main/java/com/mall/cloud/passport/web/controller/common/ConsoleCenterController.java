package com.mall.cloud.passport.web.controller.common;

import com.google.common.collect.Lists;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.component.authorize.ApplicationLoginAuthorize;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.persistence.controller.Controller;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.service.AdminAuthorizeService;
import com.mall.cloud.passport.api.service.LoginServerService;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.springframework.web.bind.annotation.*;

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
        try {
            AdminUser adminUser = loginServerService.queryAdminUser(account, password);
            List<String> resourceList = Lists.newLinkedList();
            String token = login(adminUser.getId(), resourceList, adminAuthorize);
            result.putResult("token", token);
        } catch (ApplicationServerException exception) {
            logger.error("用户登陆失败，账号:{},密码：{},TRACE:e", account, password, exception);
            result.setError("系统繁忙，请稍后再试!");
        }
        return result.parseToJson(result);
    }

    /**
     * 推出登陆
     *
     * @param request 请求
     * @return 结果
     */
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


}
