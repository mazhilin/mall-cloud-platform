package com.mall.cloud.console.web.controller.system;

import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>封装Qicloud项目RoleCenterController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-27 15:26
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping("/api/console/role/")
public class ConsoleRoleController extends BaseController {
    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize  页码数
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "list", produces = Constants.APPLICATION_JSON)
    public String list(
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
     * @param pageCount 条目数
     * @param name      菜单名称
     * @return 返回结果
     * @throws PassportServerException
     */
    @PostMapping(value = "update", produces = Constants.APPLICATION_JSON)
    public String update(
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
