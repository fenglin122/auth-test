package com.cehome.cloud.user.service;

import com.cehome.cloud.user.dao.RolePermissionMapper;
import com.cehome.cloud.user.model.po.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色资源 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-03-29
 */
@Service
public class RolePermissionInnerService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public int deleteByRoleId(Integer roleId, Set<Integer> permissionIds) {
        return rolePermissionMapper.deleteByRoleId(roleId, permissionIds);
    }

    public int deleteByPermissionId(Integer permissionId, Set<Integer> roleIds) {
        return rolePermissionMapper.deleteByPermissionId(permissionId, roleIds);
    }

    public int deleteByRole(Integer roleId) {
        return rolePermissionMapper.deleteByRole(roleId);
    }

    public int deleteByPermission(Integer permissionId) {
        return rolePermissionMapper.deleteByPermission(permissionId);
    }

    public int insert(Set<RolePermission> rolePermissions) {
        return rolePermissionMapper.insert(rolePermissions);
    }

    public List<RolePermission> selectByRole(Integer roleId) {
        return rolePermissionMapper.selectByRole(roleId);
    }

    public List<RolePermission> selectByPermission(Integer permissionId) {
        return rolePermissionMapper.selectByPermission(permissionId);
    }
}
