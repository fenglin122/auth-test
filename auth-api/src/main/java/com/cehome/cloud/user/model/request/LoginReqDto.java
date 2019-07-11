package com.cehome.cloud.user.model.request;

import com.alibaba.fastjson.JSON;

/**
 * Created by hyl on 2019/04/04
 **/
public class LoginReqDto {

    private String loginName;

    private String password;

    private Boolean rememberMe;

    private String authCode;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
