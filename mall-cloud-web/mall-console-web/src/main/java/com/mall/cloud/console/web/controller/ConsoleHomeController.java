package com.mall.cloud.console.web.controller;

import com.mall.cloud.common.annotation.ApplicationAuthorize;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.ResponseType;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.exception.ConsoleServerException;
import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.persistence.controller.Controller;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.common.utils.ObjectBeanUtil;
import com.mall.cloud.common.utils.SequenceServerUtil;
import com.mall.cloud.console.api.service.AuthorityServerService;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.service.AdminAuthorizeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
public class ConsoleHomeController extends BaseController implements Controller {
    @DubboConsumerClient
    private AdminAuthorizeService adminAuthorize;
    @DubboConsumerClient
    private AuthorityServerService authorityServerService;

    public static void main(String[] args) {
        //
        AdminUser adminUser = new AdminUser();
        adminUser.setId(String.valueOf(SequenceServerUtil.getInstance().produceId()));
        adminUser.setName("mmmm");
        System.out.println(ObjectBeanUtil.beanToMap(adminUser));

        Map<String, Object> stringObjectMap = ObjectBeanUtil.beanToMap(adminUser);
        System.out.println(ObjectBeanUtil.mapToBean(stringObjectMap, adminUser));

    }

    /**
     * 返回登录信息
     *
     * @return 返回结果
     */
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "/toLogin", produces = "application/json;charset=UTF-8")
    public String toLogin() {
        ResponseResult result = new ResponseResult();
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            result.setError(ResponseType.EXPIRE.code(), "请重新登录！");
        }
        return result.parseToJson(result);
    }

    /**
     * 用于前台检测登录
     *
     * @return 返回结果
     */
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "/isLogin", produces = "application/json;charset=UTF-8")
    public String isLogin() {
        ResponseResult result = new ResponseResult();
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            result.setError(ResponseType.EXPIRE.code(), "请重新登录！");
        }
        return result.parseToJson(result);
    }

    /**
     * 用户信息
     *
     * @return 返回结果
     */
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "/userInfo", produces = "application/json;charset=UTF-8")
    public String userInfo() {
        ResponseResult result = new ResponseResult();
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        result.putResult("user", user);
        return result.parseToJson(result);
    }

    /**
     * 用户信息
     *
     * @return 返回结果
     */
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "/showMenuList", produces = "application/json;charset=UTF-8")
    public String showMenuList(
            @RequestParam(value = "parentId", required = false, defaultValue = "") String parentId
    ) throws ConsoleServerException {
        ResponseResult response = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        // [2].依据用户类型查询菜单数据
        try {
            response = authorityServerService.showMenuList(response, user.getId(), parentId);
        } catch (Exception e) {
            logger.error("获取用户绑定菜单失败:{}", user);
            response.setError("系统繁忙，请稍后再试");
        }
        return response.parseToJson(response);
    }


}
