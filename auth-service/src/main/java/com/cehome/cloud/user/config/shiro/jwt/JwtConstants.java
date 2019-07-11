package com.cehome.cloud.user.config.shiro.jwt;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/9/29.
 */
public class JwtConstants {

    public final static String JWT_TOKEN="jwt_token";

    public final static String LOGIN_URL = "/user/login";

    public final static String TO_LOGIN_URL = "/login";

    public final static String USER_ADMIN_LOGIN_NAME = "admin";
    /**
     * 用户缓存命名空间
     */
    public static final String CACHE_NAMESPACE = "user:";
    /**
     * 登录token缓存名称
     */
    public static final String LOGIN_TOKEN = "loginToken:";

    public static List<String> NONE_URL = Arrays.asList("/manager/user/authcode",
            "/manager/user/login",
            "/manager/swagger-ui.html");

    public final static String PERMS = "perms";
    public final static String ROLES = "roles";



}
