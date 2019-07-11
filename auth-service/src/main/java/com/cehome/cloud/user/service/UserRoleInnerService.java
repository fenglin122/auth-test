package com.cehome.cloud.user.service;

import com.cehome.cloud.user.mapper.UserRoleMapper;
import com.cehome.cloud.user.model.po.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-03-29
 */
@Service
public class UserRoleInnerService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public int add(Set<UserRole> userRoles) {
        return userRoleMapper.insert(userRoles);
    }

    public int deleteByRoleIds(Integer userId, Set<Integer> roleIds) {
        return userRoleMapper.deleteByUserId(userId, roleIds);
    }

    public int deleteByUserIds(Integer roleId, Set<Integer> userIds) {
        return userRoleMapper.deleteByRoleId(roleId, userIds);
    }

    public int deleteByUser(Integer userId) {
        return userRoleMapper.deleteByUser(userId);
    }

    public int deleteByRole(Integer roleId) {
        return userRoleMapper.deleteByRole(roleId);
    }

    public List<UserRole> selectByRoleId(Integer roleId) {
        return userRoleMapper.selectByRoleId(roleId);
    }

    public List<UserRole> selectByUserId(Integer userId) {
        return userRoleMapper.selectByUserId(userId);
    }
}
