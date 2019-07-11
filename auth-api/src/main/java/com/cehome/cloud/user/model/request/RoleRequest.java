package com.cehome.cloud.user.model.request;

import com.alibaba.fastjson.JSON;

/**
 * Created by hyl on 2019/04/10
 **/
public class RoleRequest {

    private Integer id;

    /**
     * 角色名
     */
    private String name;
    /**
     * 排序号
     */
    private Integer seq;
    /**
     * 简介
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 平台ID
     */
    private Integer paltformId;

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
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getPaltformId() {
        return paltformId;
    }

    public void setPaltformId(Integer paltformId) {
        this.paltformId = paltformId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
