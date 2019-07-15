package com.cehome.cloud.user.model.request;

import com.alibaba.fastjson.JSON;
import com.cehome.utils.FastjsonUtils;

/**
 * Created by hyl on 2019/04/09
 **/
public class ResourceReqDto {

    private Integer id;

    private String name;

    private String url;

    private String description;

    /**
     * 菜单权限ID
     */
    private Integer pid;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
