package com.cehome.cloud.user.config.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {
	private static final long serialVersionUID = 9217639903967592166L;

	private String token;

    public String getToken() {
        return token;
    }

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String toString(){
        return token;
    }
}
