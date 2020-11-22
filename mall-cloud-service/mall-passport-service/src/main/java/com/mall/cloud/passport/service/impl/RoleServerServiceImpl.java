package com.mall.cloud.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.cloud.common.annotation.dubbo.DubboProviderServer;
import com.mall.cloud.common.constant.Constants;
import com.mall.cloud.common.exception.PassportServerException;
import com.mall.cloud.common.persistence.service.BaseServerService;
import com.mall.cloud.common.restful.DatagridResult;
import com.mall.cloud.common.utils.CheckEmptyUtil;
import com.mall.cloud.model.entity.system.MenuInfo;
import com.mall.cloud.model.entity.system.RoleInfo;
import com.mall.cloud.model.entity.user.AdminUser;
import com.mall.cloud.model.mapper.system.RoleInfoMapper;
import com.mall.cloud.passport.api.param.RequestRoleParam;
import com.mall.cloud.passport.api.service.RoleServerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>封装Qicloud项目RoleServerServiceImpl类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-22 16:04
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@DubboProviderServer
public class RoleServerServiceImpl extends BaseServerService implements RoleServerService {
    @Resource
    private RoleInfoMapper roleInfoMapper;

    /**
     * 分页查询菜单列表
     *
     * @param pageSize  页码数
     * @param pageLimit 条目数
     * @param param     参数对象
     * @return 返回结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public DatagridResult list(Integer pageSize, Integer pageLimit, RequestRoleParam param) throws PassportServerException {
        DatagridResult result = new DatagridResult();
        PageHelper.startPage(pageSize, pageLimit);
        //查询列表
        QueryWrapper<RoleInfo> queryRole = new QueryWrapper<>();
        if (CheckEmptyUtil.isNotEmpty(param.getName())) {
            queryRole.lambda().eq(RoleInfo::getName, param.getName());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getScope())) {
            queryRole.lambda().eq(RoleInfo::getScope, param.getScope());
        }
        if (CheckEmptyUtil.isNotEmpty(param.getStatus())) {
            queryRole.lambda().eq(RoleInfo::getStatus, param.getStatus());
        }
        queryRole.lambda().eq(RoleInfo::getIsDelete, Constants.NO);
        queryRole.lambda().orderByDesc(RoleInfo::getCreateTime);
        List<RoleInfo> queryList = roleInfoMapper.selectList(queryRole);
        result.setDataList(queryList);
        PageInfo<RoleInfo> pageInfo = new PageInfo<>(queryList);
        result.setPageCount(pageInfo.getTotal());
        return result;
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public int save(MenuInfo menu) throws PassportServerException {
        return 0;
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     * @throws PassportServerException 异常消息
     */
    @Override
    public int edit(MenuInfo menu) throws PassportServerException {
        return 0;
    }

    /**
     * 根据ID查询菜单详情
     *
     * @param id 菜单ID
     * @return 菜单详情
     */
    @Override
    public MenuInfo detail(Long id) {
        return null;
    }
}
