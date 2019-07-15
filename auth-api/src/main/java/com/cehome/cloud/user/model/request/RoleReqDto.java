package com.cehome.cloud.user.model.request;

import com.cehome.utils.FastjsonUtils;

/**
 * Created by hyl on 2019/04/10
 **/
public class RoleReqDto {

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
    private Integer platformId;

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


    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    @Override
    public String toString() {
        return FastjsonUtils.toJSONString(this);
    }
}
