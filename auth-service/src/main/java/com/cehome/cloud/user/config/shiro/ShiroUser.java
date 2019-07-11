/**
 *
 */
package com.cehome.cloud.user.config.shiro;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * hyl by 2019-4-1
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    
    private String id;
    private String loginName;
    private String name;
    @JsonIgnore
    private String password;
    private String salt;
    private String env;

    public ShiroUser(){}

    public ShiroUser(String loginName) {
        this.loginName = loginName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }
}