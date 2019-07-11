package com.cehome.cloud.user.model.request;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by hyl on 2019/04/17
 **/
public class PermissionGrantReqDto {

    private Integer roleId;
    private List<Integer> permissionIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
