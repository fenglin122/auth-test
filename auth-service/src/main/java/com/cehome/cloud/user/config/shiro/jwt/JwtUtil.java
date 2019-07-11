package com.cehome.cloud.user.config.shiro.jwt;

import com.cehome.cloud.user.config.shiro.ShiroUser;
import com.cehome.utils.PropertyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.beans.PropertyDescriptor;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * 构造及解析jwt的工具类
 */
public class JwtUtil {

    private static String audience = "";
    private static String issuer = "";
    private static long expiresSecond = 0;
    private static String base64Security="";

    static {
        audience = PropertyUtils.getStringValue("jwt.info.clientId");
        if (StringUtils.isEmpty(audience)) {
            throw new IllegalArgumentException("jwt.info.clientId 参数不存在");
        }
        base64Security = PropertyUtils.getStringValue("jwt.info.base64Secret");
        if (StringUtils.isEmpty(audience)) {
            throw new IllegalArgumentException("jwt.info.base64Secret 参数不存在");
        }
        issuer = PropertyUtils.getStringValue("jwt.info.name");
        if (StringUtils.isEmpty(audience)) {
            throw new IllegalArgumentException("jwt.info.name 参数不存在");
        }
        String expires = PropertyUtils.getStringValue("jwt.info.expiresSecond");
        if (StringUtils.isEmpty(expires)) {
            expiresSecond = -1;
        }
        expiresSecond = Long.parseLong(expires) * 1000;
    }

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch(Exception ex) {
            return null;
        }
    }
    /**
     * 解析jwt
     */
    public static ShiroUser parse(String jsonWebToken){
        Claims claims = parseJWT(jsonWebToken);
        return toUserInfo(claims);
    }

    /**
     * 解析jwt
     */
    public static String parseLoginName(String jsonWebToken){
        Claims claims = parseJWT(jsonWebToken);
        try {
            if (claims != null){
                return (String) claims.get("loginName");
            }
        }catch (Exception e){

        }
        return null;
    }

    private static ShiroUser toUserInfo(Claims claims) {
        if (claims != null) {
            try {
                //获取一下客户端传过来的关键的身份id
                Object _userId=claims.get("id");
                String userId = _userId != null ? _userId.toString() : null;
                String loginName = (String) claims.get("loginName");
                String env=(String) claims.get("env");
                String name=(String) claims.get("name");
                ShiroUser userInfo = new ShiroUser(loginName);
                userInfo.setEnv(env);
                userInfo.setId(userId);
                userInfo.setName(name);
                userInfo.setSalt(claims.getSubject());
                return userInfo;
            }catch (Exception e){}
        }
        return null;
    }
    /**
     * 构建jwt
     */
    public static String createJWT(ShiroUser userInfo) {
        if(userInfo == null) return null;
        if(StringUtils.isEmpty(userInfo.getEnv())) {
            userInfo.setEnv(PropertyUtils.getStringValue("saas.project.env"));
        }
        Map<String,Object> source = new HashMap<String, Object>();
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(userInfo);
        for (int i = 0; i < descriptors.length; i++) {
            String name = descriptors[i].getName();
            if (JwtConstants.PERMS.equals(name) || JwtConstants.ROLES.equals(name)){
                continue;
            }
            Object value=null;
            try {
                value=propertyUtilsBean.getNestedProperty(userInfo, name);
            } catch (Exception e) {}
            source.put(name, value);
        }
        //生成JWT
        String accessToken= createJWT(source);
        return accessToken;
    }

    /**
     * 构建jwt
     */
    public static String createJWT(Map<String, Object> source) {
        if(source == null || source.size() == 0) return null;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setClaims(source)
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey)
                .setSubject(source.get("salt").toString());
        if (expiresSecond >= 0) {
            builder.setExpiration(new Date(nowMillis + expiresSecond));     //设置过期时间
        }
        //生成JWT
        return builder.compact();
    }

    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(now);
    }


}
