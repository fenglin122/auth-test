package com.cehome.cloud.user.service;

import com.cehome.cloud.user.mapper.PermissionMapper;
import com.cehome.cloud.user.model.po.Permission;
import com.cehome.cloud.user.model.po.Role;
import com.cehome.cloud.user.model.po.RolePermission;
import com.cehome.cloud.user.util.TreeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-03-29
 */
@Service
public class PermissionInnerService {

    Logger logger = LoggerFactory.getLogger(PermissionInnerService.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleInnerService roleInnerService;

    @Autowired
    private RolePermissionInnerService rolePermissionInnerService;

    public int add(Permission permission) {
        permission.setCreateTime(Calendar.getInstance().getTime());
        permission.setUpdateTime(Calendar.getInstance().getTime());
        return permissionMapper.insert(permission);
    }

    public int update(Permission permission) {
        permission.setUpdateTime(Calendar.getInstance().getTime());
        return permissionMapper.update(permission);
    }

    public Permission selectById(Integer id) {
        return permissionMapper.selectById(id);
    }

    public int deleteById(Integer id) {
        return permissionMapper.deleteById(id);
    }

    public int grantPermission(Integer roleId, List<Integer> permissionIds) {
        Role role = roleInnerService.selectById(roleId);
        if (role == null){
            throw new NullPointerException("角色ID["+roleId+"]不存在");
        }
        if (CollectionUtils.isEmpty(permissionIds)){
            throw new NullPointerException("权限ID为空");
        }
        rolePermissionInnerService.deleteByRole(roleId);
        Set<RolePermission> rolePermissions = Sets.newHashSet();
        permissionIds.stream().forEach(permissionId -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermission.setCreateTime(Calendar.getInstance().getTime());
            rolePermission.setUpdateTime(Calendar.getInstance().getTime());
            rolePermissions.add(rolePermission);
        });
        return rolePermissionInnerService.insert(rolePermissions);
    }

    public List<Permission> permssionAll() {
        List<Permission> permissions = permissionMapper.permissionAll();
        logger.info("permssionAll:{}",permissions);
        return toTree(permissions);
    }

    public List<Permission> permssionList(Integer tenantId) {
        return permissionMapper.permissionAll();
    }

    public List<Permission> selectByUser(Integer userId, Integer tenantId) {
        return toTree(listByUser(userId));
    }

    public List<Permission> listByUser(Integer userId) {
        List<Permission> permissions = selectListByUser(userId);
        List<Permission> list = Lists.newArrayList();
        List<Integer> ids = permissions.stream().map(Permission::getId).collect(Collectors.toList());
        permissions.stream()
                .filter(permission -> permission.getParentId() != null)
                .forEach(permission ->
                        getParentPerm(list,permissionMapper.selectById(permission.getParentId()),ids)
                );
        list.addAll(permissions);
        logger.info("selectByUser:{}",list);
        return list;
    }

    public List<Permission> selectListByUser(Integer userId) {
        return permissionMapper.selectByUser(userId);
    }

    public List<Integer> selectByRole(Integer roleId) {
        List<Permission> permissions = permissionMapper.selectByRole(roleId);
        if (CollectionUtils.isEmpty(permissions)){
            return Lists.newArrayList();
        }
        return permissions.stream().map(Permission::getId).collect(Collectors.toList());
    }

    /**
     * 转换为树形结构
     */
    private List<Permission> toTree(List<Permission> menuList) {
        return TreeUtil.toTree(menuList, "id", "parentId", "childPermissions", Permission.class);
    }

    private void getParentPerm(List<Permission> list,Permission permission,List<Integer> ids){
        if (!ids.contains(permission.getId())){
            if (permission.getParentId() != null){
                Permission parentPerm = permissionMapper.selectById(permission.getParentId());
                getParentPerm(list,parentPerm,ids);
            }
            list.add(permission);
            ids.add(permission.getId());
        }
    }
}
