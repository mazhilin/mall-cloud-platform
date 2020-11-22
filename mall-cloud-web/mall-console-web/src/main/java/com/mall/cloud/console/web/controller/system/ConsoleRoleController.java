package com.mall.cloud.console.web.controller.system;

import com.mall.cloud.common.annotation.ApplicationAuthorize;
import com.mall.cloud.common.annotation.dubbo.DubboConsumerClient;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.constant.ScopeType;
import com.mall.cloud.common.exception.ConsoleServerException;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.controller.BaseController;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.restful.ResponseResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.MenuInfo;
import com.mall.cloud.model.entity.system.RoleInfo;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.param.RequestMenuParam;
import com.mall.cloud.passport.api.param.RequestRoleParam;
import com.mall.cloud.passport.api.service.RoleServerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @DubboConsumerClient
    private RoleServerService roleServerService;

    /**
     *
     * @param pageSize
     * @param pageLimit
     * @param name
     * @param status
     * @param scope
     * @param companyId
     * @return
     * @throws PassportServerException
     */
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "list", produces = "application/json;charset=UTF-8")
    public String list(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageLimit", required = false, defaultValue = "10") Integer pageLimit,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "scope", required = false) Integer scope,
            @RequestParam(value = "companyId", required = false) String companyId
    ) throws PassportServerException {
        ResponseResult result = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        // [2] 业务请求处理
        try {
            RequestRoleParam  param = new RequestRoleParam();
            param.setName(name);
            param.setCompanyId(companyId);
            param.setName(name);
            param.setScope(scope);
            param.setStatus(status);
            DatagridResult datagrid = roleServerService.list(pageSize, pageLimit, param);
            List<RoleInfo> roleList = datagrid.getDataList();
            result.putResult("list", roleList);
            result.putResult("pageCount", datagrid.getPageCount());
            result.putResult("pageSize", pageSize);
            result.putResult("pageLimit", pageLimit);
            result.putResult("totalPage", datagrid.getTotalPage());
        } catch (PassportServerException exception) {
            logger.error("查询后台用户列表失败:{},{}", user.getId(), exception);
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
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "save", produces = "application/json;charset=UTF-8")
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
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "edit", produces = "application/json;charset=UTF-8")
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
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "update", produces = "application/json;charset=UTF-8")
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
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "delete", produces = "application/json;charset=UTF-8")
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
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "show", produces = "application/json;charset=UTF-8")
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
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "detail", produces = "application/json;charset=UTF-8")
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
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "sort", produces = "application/json;charset=UTF-8")
    public String sort(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageCount", required = false, defaultValue = "10") Integer pageCount,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) throws PassportServerException {
        return StringUtils.EMPTY;
    }
}
