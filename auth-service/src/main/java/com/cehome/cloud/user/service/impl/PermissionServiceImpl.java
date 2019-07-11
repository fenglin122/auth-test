package com.cehome.cloud.user.service.impl;

import com.cehome.cloud.user.api.PermissionService;
import com.cehome.cloud.user.config.shiro.ShiroUser;
import com.cehome.cloud.user.config.shiro.UserContext;
import com.cehome.cloud.user.config.shiro.jwt.JwtConstants;
import com.cehome.cloud.user.model.po.Permission;
import com.cehome.cloud.user.model.request.PermissionGrantReqDto;
import com.cehome.cloud.user.model.request.PermissionReqDto;
import com.cehome.cloud.user.service.PermissionInnerService;
import com.cehome.cloud.user.util.BeanUtils;
import com.cehome.utils.exception.MicroserviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/11/ 15:02
 */
@RestController
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionInnerService permissionInnerService;

    public Integer add(@RequestBody PermissionReqDto permissionReqDto) {
        Permission permission = BeanUtils.copy(permissionReqDto,Permission.class);
        return permissionInnerService.add(permission);
    }

    public Integer update(@RequestBody PermissionReqDto permissionReqDto) {
        Permission permission = BeanUtils.copy(permissionReqDto,Permission.class);
        return permissionInnerService.update(permission);
    }

    public Permission getById(Integer permission) {
        return permissionInnerService.selectById(permission);
    }

    public Integer deleteById(Integer id) {
        return permissionInnerService.deleteById(id);
    }

    public Integer grantPerm(@RequestBody PermissionGrantReqDto permissionGrantReqDto){
        if (null == permissionGrantReqDto){
            throw new MicroserviceException("参数为空");
        }
        return permissionInnerService.grantPermission(permissionGrantReqDto.getRoleId(), permissionGrantReqDto.getPermissionIds());
    }

    public List<Permission> listPermsTree() {
        return permissionInnerService.permssionAll();
    }

    public List<Permission> listPerms() {
        return permissionInnerService.permssionList();
    }

    public List<Permission> listPermsTreeByUser() {
        ShiroUser shiroUser = UserContext.getCurrentUser();
        if (JwtConstants.USER_ADMIN_LOGIN_NAME.equals(shiroUser.getLoginName())){
            return permissionInnerService.permssionAll();
        }
        return permissionInnerService.selectByUser(Integer.parseInt(shiroUser.getId()));
    }

    public List<Permission> listPremsByUser() {
        ShiroUser shiroUser = UserContext.getCurrentUser();
        if (JwtConstants.USER_ADMIN_LOGIN_NAME.equals(shiroUser.getLoginName())){
            return permissionInnerService.permssionList();
        }
        return permissionInnerService.selectListByUser(Integer.parseInt(shiroUser.getId()));
    }

    public List<Integer> listpermIdsByRole(Integer roleId) {
        return permissionInnerService.selectByRole(roleId);
    }
}
