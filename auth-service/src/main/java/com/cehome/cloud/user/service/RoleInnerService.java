package com.cehome.cloud.user.service;

import com.cehome.cloud.common.dao.base.Page;
import com.cehome.cloud.user.config.shiro.jwt.JwtConstants;
import com.cehome.cloud.user.dao.RoleMapper;
import com.cehome.cloud.user.model.enums.RoleStatus;
import com.cehome.cloud.user.model.po.Permission;
import com.cehome.cloud.user.model.po.Role;
import com.cehome.cloud.user.model.po.UserRole;
import com.cehome.cloud.user.model.query.QueryBase;
import com.cehome.cloud.user.model.query.RoleQuery;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-03-29
 */
@Service
public class RoleInnerService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleInnerService userRoleInnerService;

    @Autowired
    private RolePermissionInnerService rolePermissionInnerService;

    @Autowired
    private ResourceInnerService resourceInnerService;
    @Autowired
    private PermissionInnerService permissionInnerService;


    public Map<String, Set<String>> selectPermissionMapByUserId(Integer userId) {
        List<UserRole> userRoles = userRoleInnerService.selectByUserId(userId);
        List<Permission> permissions = permissionInnerService.listByUser(userId);
        Map<String,Set<String>> permissionMap = Maps.newHashMap();
        Set<String> rolesIds = userRoles.stream().map(userRole -> userRole.getRoleId()+"").collect(Collectors.toSet());
        Set<String> perms = Sets.newHashSet();
        permissions.stream().forEach(permission -> {
            perms.add(permission.getPerms());
            resourceInnerService.selectByPid(permission.getId()).stream().forEach(resource -> perms.add(resource.getPerms()));
        });
        permissionMap.put(JwtConstants.ROLES,rolesIds);
        permissionMap.put(JwtConstants.PERMS,perms);
        return permissionMap;
    }

    @Transactional(rollbackFor={Exception.class})
    public int deleteById(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Transactional(rollbackFor={Exception.class})
    public int add(Role role) {
        role.setCreateTime(Calendar.getInstance().getTime());
        role.setUpdateTime(Calendar.getInstance().getTime());
        return roleMapper.insert(role);
    }

    public Role selectById(Integer id) {
        return roleMapper.selectById(id);
    }

    @Transactional(rollbackFor={Exception.class})
    public int update(Role role) {
        role.setUpdateTime(Calendar.getInstance().getTime());
        return roleMapper.update(role);
    }

    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    public Page<Role> search(String name, Integer status, Integer platformId, Integer pageIndex, Integer pageSize) {
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setPlatformId(platformId);
        roleQuery.setName(name);
        roleQuery.setStatus(RoleStatus.valueOf(status));
        int count = roleMapper.selectCount(roleQuery);
        Page page = new Page();
        if (count > 0){
            if (pageIndex == null){
                pageIndex = 1;
            }
            if (pageSize == null){
                pageSize = 20;
            }
            roleQuery.setCount(pageSize);
            roleQuery.setStartIndex((pageIndex-1)*pageSize);
            roleQuery.setOrder(RoleQuery.RoleOrderBy.UPDATETIME);
            roleQuery.setSort(QueryBase.QuerySort.DESC);
            page.setDatas(roleMapper.selectList(roleQuery));
            page.setPageIndex(pageIndex);
            page.setPageOffset((pageIndex-1)*pageSize);
            page.setPageSize(pageSize);
            page.setTotalRecord(count);
            int totalPage = count>0?(count/pageSize+(count%pageSize==0?0:1)):0;
            page.setTotalPage(totalPage);
        }
        return page;
    }

    public List<Role> listByUser(Integer userId, Integer tenantId) {
        return null;
    }
}
