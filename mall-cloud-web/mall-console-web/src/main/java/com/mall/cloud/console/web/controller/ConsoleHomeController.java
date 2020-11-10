package com.mall.cloud.console.web.controller;

import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.restful.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * <p>封装Qicloud项目ConsoleHomeController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 19:51
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping(value = "/api/console/home", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
public class ConsoleHomeController extends BaseController {
    /**
     * 返回登录信息
     *
     * @return 返回结果
     */
    @PostMapping(value = "/toLogin", produces = "application/json;charset=UTF-8")
    public String toLogin() {
        ResponseResult response = new ResponseResult();
        response.setMessage("请登陆");
        response.setCode(800);
        return response.parseToJson(response);
    }

    /**
     * 用于前台检测登录
     *
     * @return 返回结果
     */
    @GetMapping(value = "isLogin", produces = "application/json;charset=UTF-8")
    public String isLogin() {
        ResponseResult response = new ResponseResult();
        return response.parseToJson(response);
    }
}
