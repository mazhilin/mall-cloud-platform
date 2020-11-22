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
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.passport.api.param.RequestMenuParam;
import com.mall.cloud.passport.api.service.MenuServerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>封装Qicloud项目MenuCenterController类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-27 15:30
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@RestController
@RequestMapping(value = "/api/console/menu/")
public class ConsoleMenuController extends BaseController {

    @DubboConsumerClient
    private MenuServerService menuServerService;

    /**
     * 后台管理平台-系统中心-菜单管理-菜单选项卡
     *
     * @return 返回结果
     * @throws PassportServerException
     */
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "category", produces = "application/json;charset=UTF-8")
    public String category() throws PassportServerException {
        ResponseResult result = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        try {
            List<MenuInfo> menuList = menuServerService.category(user.getId());
            result.putResult("list", menuList);
        } catch (PassportServerException exception) {
            logger.error("查询后台用户列表失败:{},{}", user.getId(), exception);
            result.setError("系统繁忙，请稍后再试");
        }
        return result.parseToJson(result);
    }

    /**
     * 后台管理平台-系统中心-菜单管理-列表
     *
     * @param pageSize   页码数
     * @param pageLimit  条目数
     * @param categoryId 菜单栏目id
     * @param name       菜单名称
     * @param scope      菜单作用域
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @ApplicationAuthorize(authorizeResources = false, authorizeScope = ScopeType.WEB)
    @PostMapping(value = "list", produces = "application/json;charset=UTF-8")
    public String list(
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            @RequestParam(value = "pageLimit", required = false, defaultValue = "10") Integer pageLimit,
            @RequestParam(value = "categoryId", required = false) String categoryId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "menuAuth", required = false) Integer menuAuth,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "scope", required = false) Integer scope
    ) throws PassportServerException {
        ResponseResult result = new ResponseResult();
        // [1].用户登录鉴权
        AdminUser user = (AdminUser) request.getAttribute(Constants.ADMIN_USER);
        if (CheckEmptyUtil.isEmpty(user)) {
            throw new ConsoleServerException("系统繁忙，请稍后再试!");
        }
        // [2] 业务请求处理
        try {
            RequestMenuParam param = new RequestMenuParam();
            param.setUserId(user.getId());
            param.setName(name);
            param.setParentId(categoryId);
            param.setName(name);
            param.setScope(scope);
            param.setStatus(status);
            param.setMenuAuth(menuAuth);
            DatagridResult datagrid = menuServerService.list(pageSize, pageLimit, param);
            List<MenuInfo> menuList = datagrid.getDataList();
            result.putResult("list", menuList);
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
