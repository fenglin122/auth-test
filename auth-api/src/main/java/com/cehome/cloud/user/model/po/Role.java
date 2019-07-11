package com.cehome.cloud.user.model.po;

import java.util.Date;

public class Role {
    private Integer id;

    private String name;

    private Integer seq;

    private String description;

    private Integer deleteStatus;

    private Integer paltformId;

    private Date createTime;

    private Date updateTime;

    private String permissionRoleId;

    private Integer isAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getPaltformId() {
        return paltformId;
    }

    public void setPaltformId(Integer paltformId) {
        this.paltformId = paltformId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getPermissionRoleId() {
        return permissionRoleId;
    }

    public void setPermissionRoleId(String permissionRoleId) {
        this.permissionRoleId = permissionRoleId == null ? null : permissionRoleId.trim();
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }
}