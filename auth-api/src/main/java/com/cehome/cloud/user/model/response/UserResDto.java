package com.cehome.cloud.user.model.response;

import com.cehome.cloud.user.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class UserResDto {

    /**
     * 主键id
     */
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
     * 密码加密盐
     */
    @JsonIgnore
    private String salt;
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
    private Integer status;
    /**
     * 所属机构
     */
    private Integer organizationId;
    /**
     * 租户ID
     */
    private Integer tenantId;
    /**
     * 平台ID
     */
    private Integer platformId;
    /**
     * 创建时间
     */
    @JsonIgnore
    private Long createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    private Long updateTime;
    /**
     * 是否有效（逻辑删除）
     */
    private Integer isDelete;


    private String createTimeStr;
    private String updateTimeStr;

    private List<Integer> roleIds;

    private String roles;

    public String getCreateTimeStr() {
        if (null != createTime && createTime > 0){
            return DateUtils.longToString(createTime,"yyyy-MM-dd HH:mm:ss");
        }
        return "";
    }

    public String getUpdateTimeStr() {
        if (null != updateTime && updateTime > 0){
            return DateUtils.longToString(updateTime,"yyyy-MM-dd HH:mm:ss");
        }
        return "";
    }


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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserResDto other = (UserResDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
