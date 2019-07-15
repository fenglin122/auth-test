package com.cehome.cloud.user.model.request;

import com.alibaba.fastjson.JSON;
import com.cehome.utils.FastjsonUtils;

/**
 * Created by hyl on 2019/04/09
 **/
public class PermissionReqDto {

    private Integer id;
    /**
     * 权限名称：菜单名或者按钮名称
     */
    private String name;
    /**
     * 菜单路径或者按钮标识
     */
    private String url;
    /**
     * 菜单或按钮图标
     */
    private String icon;
    /**
     * 父级菜单id
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     *  权限类别：1-菜单，2-按钮
     */
    private Integer type;
    /**
     * 平台ID
     */
    private Integer paltformId;

    private String perms;

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
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPaltformId() {
        return paltformId;
    }

    public void setPaltformId(Integer paltformId) {
        this.paltformId = paltformId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return FastjsonUtils.toJSONString(this);
    }
}
