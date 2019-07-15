package com.cehome.cloud.user.dao;

import com.cehome.cloud.user.model.po.Role;
import com.cehome.cloud.user.model.query.RoleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper {
    int deleteById(@Param("id") Integer id);

    int insert(@Param("role") Role role);

    Role selectById(@Param("id") Integer id);

    int update(@Param("role") Role role);

    List<Role> selectAll();

    int selectCount(@Param("query") RoleQuery roleQuery);

    List<Role> selectList(@Param("query") RoleQuery roleQuery);

    List<Role> selectByUser(@Param("userId") Integer userId, @Param("tenantId") Integer tenantId);
}