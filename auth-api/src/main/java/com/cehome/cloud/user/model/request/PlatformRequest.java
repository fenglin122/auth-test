package com.cehome.cloud.user.model.request;

import com.alibaba.fastjson.JSON;

/**
 * Created by hyl on 2019/04/09
 **/
public class PlatformRequest {
    private Integer id;
    private String name;
    private String showName;
    private Integer status;
    private Integer administratorId;

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

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
