package com.cehome.cloud.user.dao;


import com.cehome.cloud.user.model.po.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper  extends BaseMapper{
    int deleteById(@Param("id") Integer id);

    int insert(@Param("perm") Permission permission);

    Permission selectById(@Param("id") Integer id);

    int update(@Param("perm") Permission permission);

    List<Permission> permissionAll();

    List<Permission> selectByUser(@Param("userId") Integer userId);

    List<Permission> selectByRole(@Param("roleId") Integer roleId);

}