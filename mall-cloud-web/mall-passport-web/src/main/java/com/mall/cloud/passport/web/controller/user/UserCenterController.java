package com.mall.cloud.passport.web.controller.user;

import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.param.RequestUserParam;
import com.mall.cloud.passport.api.service.UserServerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>封装Qicloud项目AdminUserController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-27 15:02
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping("/api/user/center/")
public class UserCenterController extends BaseController {
    @DubboConsumerClient
    private UserServerService userServerService;

    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param status    状态
     * @param name      名字
     * @param account   账户
     * @param mobile    手机
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @PostMapping(value = "list", produces = Constants.APPLICATION_JSON)
    public String list(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageLimit", required = false, defaultValue = "10") Integer pageLimit,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "mobile", required = false) String mobile
    ) throws PassportServerException {
        ResponseResult result = new ResponseResult();
        try {
            RequestUserParam param = new RequestUserParam();
            param.setName(name);
            param.setAccount(account);
            param.setMobile(mobile);
            param.setStatus(status);
            DatagridResult datagrid = userServerService.list(pageSize, pageLimit, param);
            List<AdminUser> userList = datagrid.getDataList();
            result.putResult("resultList", userList);
            result.putResult("pageCount", datagrid.getPageCount());
            result.putResult("pageSize", pageSize);
            result.putResult("pageLimit", pageLimit);
            result.putResult("totalPage", datagrid.getTotalPage());
        } catch (PassportServerException exception) {
            logger.error("查询后台用户列表失败:{},{}", name, exception);
            result.setError("系统繁忙，请稍后再试");
        }
        return result.parseToJson(result);
    }


    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "save", produces = Constants.APPLICATION_JSON)
    public String save(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageCount", required = false, defaultValue = "10") Integer pageCount,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }


    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "edit", produces = Constants.APPLICATION_JSON)
    public String edit(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageCount", required = false, defaultValue = "10") Integer pageCount,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }


    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "update", produces = Constants.APPLICATION_JSON)
    public String update(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageLimit", required = false, defaultValue = "10") Integer pageLimit,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }


    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "delete", produces = Constants.APPLICATION_JSON)
    public String delete(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageCount", required = false, defaultValue = "10") Integer pageCount,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }

    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "show", produces = Constants.APPLICATION_JSON)
    public String show(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageCount", required = false, defaultValue = "10") Integer pageCount,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }


    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "detail", produces = Constants.APPLICATION_JSON)
    public String detail(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageCount", required = false, defaultValue = "10") Integer pageCount,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }

    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "sort", produces = Constants.APPLICATION_JSON)
    public String sort(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageCount", required = false, defaultValue = "10") Integer pageCount,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }
}
