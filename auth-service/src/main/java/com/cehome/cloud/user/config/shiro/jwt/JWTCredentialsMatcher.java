package com.cehome.cloud.user.config.shiro.jwt;

import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTCredentialsMatcher implements CredentialsMatcher {
	
	private final Logger logger = LoggerFactory.getLogger(JWTCredentialsMatcher.class);
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) authenticationToken.getCredentials();
        Object stored = authenticationInfo.getCredentials();
        String salt = stored.toString();
        try {
            Claims claims = JwtUtil.parseJWT(token);
            String subject = claims.getSubject();
            return subject.equals(salt);
        } catch (Exception e) {
            logger.error("Token Error:{}", e.getMessage());
        }

        return false;
    }



}
