package com.cehome.cloud.user.dao;

import com.cehome.cloud.user.model.po.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RolePermissionMapper  extends BaseMapper{
    int deleteByRoleId(@Param("roleId") Integer roleId, @Param("permissionIds") Set<Integer> permissionIds);
    int deleteByPermissionId(@Param("permissionId") Integer permissionId, @Param("roleIds") Set<Integer> roleIds);

    int deleteByRole(@Param("roleId") Integer roleId);
    int deleteByPermission(@Param("permissionId") Integer permissionId);

    int insert(@Param("rolePermissions") Set<RolePermission> rolePermissions);

    List<RolePermission> selectByRole(@Param("roleId") Integer roleId);

    List<RolePermission> selectByPermission(@Param("permissionId") Integer permissionId);

}