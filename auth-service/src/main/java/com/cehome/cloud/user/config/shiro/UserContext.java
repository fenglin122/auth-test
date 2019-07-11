package com.cehome.cloud.user.config.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2018/9/10.
 */
public class UserContext implements AutoCloseable{

    private final static Logger logger = LoggerFactory.getLogger(UserContext.class);
    static final ThreadLocal<ShiroUser> current = new ThreadLocal<>();

    public UserContext(ShiroUser shiroUser){
        current.set(shiroUser);
    }

    public static ShiroUser getCurrentUser() {
        return current.get();
    }

    public static void setCurrentUser(ShiroUser user) {
        current.set(user);
    }

    public void close() {
        current.remove();
    }


}
