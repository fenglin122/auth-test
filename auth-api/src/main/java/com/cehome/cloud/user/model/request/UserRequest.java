package com.cehome.cloud.user.model.request;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by hyl on 2019/04/08
 **/
public class UserRequest {

    private Integer id;

    /**
     * 登陆名
     */
    private String loginName;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户类别
     */
    private Integer userType;
    /**
     * 用户状态
     */
    private Integer status = 1;
    /**
     * 所属机构
     */
    private Integer organizationId;

    private Integer platformId = 1;

    private List<String> roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
