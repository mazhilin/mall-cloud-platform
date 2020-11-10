package com.mall.cloud.passport.web.controller.common;

import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.exception.ApplicationServerException;
import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.ApplicationServerUtil;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.JsonServerUtil;
import com.mall.cloud.common.utils.MD5Util;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.service.LoginServerService;
import com.mall.cloud.passport.api.service.RedisOperationsService;
import com.mall.cloud.passport.api.service.ValueOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
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
public class ConsoleCenterController extends BaseController {

    @DubboConsumerClient
    private LoginServerService loginServerService;
    @DubboConsumerClient
    private RedisOperationsService<String, Object> redisOperationsService;
    @DubboConsumerClient
    private ValueOperationsService<String, Object> valueOperationsService;


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
        HttpSession session = Objects.requireNonNull(ApplicationServerUtil.getRequest()).getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        if (CheckEmptyUtil.isNotEmpty(adminUser)) {
            return result.parseToJson(result);
        }
        if (CheckEmptyUtil.isEmpty(account) || CheckEmptyUtil.isEmpty(password)) {
            result.setError("请填写用户名和密码！");
            return result.parseToJson(result);
        }
        if (CheckEmptyUtil.isEmpty(account)) {
            result.setError("请填写用户名!");
            return result.parseToJson(result);
        }
        if (CheckEmptyUtil.isEmpty(password)) {
            result.setError("请填写密码！");
            return result.parseToJson(result);
        }
        adminUser = loginServerService.queryAdminUser(account, password);
        if (CheckEmptyUtil.isEmpty(adminUser)) {
            result.setError("账号或密码错误！");
            return result.parseToJson(result);
        }
        valueOperationsService.set("mall:cloud:adminUser:" + adminUser.getId(), JsonServerUtil.getInstance().parseToJson(adminUser));
        session.setAttribute("adminUser", adminUser);
        session.setMaxInactiveInterval(1800);
        if (CheckEmptyUtil.isNotEmpty(auto) && Objects.equals(auto, 1)) {
            Cookie cookieAC = new Cookie("account", account);
            Cookie cookiePA = new Cookie("password", MD5Util.generate(password));
            // 设置为7天
            cookieAC.setMaxAge(7 * 60 * 60 * 24);
            cookieAC.setPath("/");
            // 设置为7天
            cookiePA.setMaxAge(7 * 60 * 60 * 24);
            cookiePA.setPath("/");
        }
        return result.parseToJson(result);
    }


}
