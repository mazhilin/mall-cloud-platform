package com.mall.cloud.model.mapper.system;

import com.mall.cloud.common.persistence.mapper.BaseMybatisMapper;
import com.mall.cloud.model.entity.system.MenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>封装Qicloud项目MenuInfoMapper类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-10 22:56
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Mapper
public interface MenuInfoMapper extends BaseMybatisMapper<MenuInfo> {

    List<MenuInfo> queryMenuTreeList(@Param("id") Long id, @Param("parentOrganizationId") Long parentOrganizationId);
}
