package com.cehome.cloud.user.dao;

import com.cehome.cloud.user.model.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserRoleMapper extends BaseMapper {
    int deleteByUserId(@Param("userId") Integer userId, @Param("roleIds") Set<Integer> roleIds);

    int deleteByRoleId(@Param("roleId") Integer roleId, @Param("userIds") Set<Integer> userIds);

    int deleteByUser(@Param("userId") Integer userId);

    int deleteByRole(@Param("roleId") Integer roleId);

    int insert(@Param("userRoles") Set<UserRole> userRoles);

    List<UserRole> selectByUserId(@Param("userId") Integer userId);

    List<UserRole> selectByRoleId(@Param("roleId") Integer roleId);

}