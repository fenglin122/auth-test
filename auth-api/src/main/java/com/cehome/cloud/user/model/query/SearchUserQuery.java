package com.cehome.cloud.user.model.query;

import com.alibaba.fastjson.JSON;

/**
 * 用户查询（抽象类）
 *
 * Created by hyl on 2019/04/03
 */
public class SearchUserQuery extends UserQuery {

    private static final long serialVersionUID = -7685197378255091199L;

    private String loginName;
    private String phone;
    /** 组织ID */
    private Integer organizationId;
    /** 平台ID */
    private Integer platformId;
    /** 是否对组织结构路径进行前置模糊查询 */
    private boolean isLikePath = false;
    /**对用户名和姓名机型模糊匹配**/
    private String accountLike;

    @Override
    public boolean check() {
        if ((platformId == null)) return false;
        if ((organizationId == null)) return false;
        return true;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isLikePath() {
        return isLikePath;
    }

    public void setLikePath(boolean likePath) {
        isLikePath = likePath;
    }

    public String getAccountLike() {
        return accountLike;
    }

    public void setAccountLike(String accountLike) {
        this.accountLike = accountLike;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
