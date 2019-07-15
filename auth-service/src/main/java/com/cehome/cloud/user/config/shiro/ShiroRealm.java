package com.cehome.cloud.user.config.shiro;

import com.cehome.cloud.user.config.shiro.jwt.JWTCredentialsMatcher;
import com.cehome.cloud.user.config.shiro.jwt.JWTToken;
import com.cehome.cloud.user.config.shiro.jwt.JwtConstants;
import com.cehome.cloud.user.config.shiro.jwt.JwtUtil;
import com.cehome.cloud.user.model.po.User;
import com.cehome.cloud.user.service.ResourceInnerService;
import com.cehome.cloud.user.service.RoleInnerService;
import com.cehome.cloud.user.service.UserInnerService;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShiroRealm extends AuthorizingRealm {
    private final Logger logggr = LoggerFactory.getLogger(ShiroRealm.class);

    private UserInnerService userInnerService;

    private RoleInnerService roleInnerService;

    private ResourceInnerService resourceInnerService;

    public ShiroRealm(UserInnerService userInnerService, RoleInnerService roleInnerService, ResourceInnerService resourceInnerService) {
        this.userInnerService = userInnerService;
        this.roleInnerService = roleInnerService;
        this.resourceInnerService = resourceInnerService;
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logggr.info("Shiro开始登录认证");
        JWTToken jwtToken = (JWTToken) authcToken;
        String token = jwtToken.getToken();
        ShiroUser shiroUser = JwtUtil.parse(token);
        if (shiroUser == null) {
            throw new AuthenticationException("token无效");
        }
        User user = userInnerService.selectById(Integer.parseInt(shiroUser.getId()));
        // 账号不存在
        if (user != null) {
            // 认证缓存信息
            return new SimpleAuthenticationInfo(token, user.getCredentialsSalt(), getName());
        }
        return null;

    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     *
     * @see {}经测试：本例中该方法的调用时机为需授权资源被访问时
     * @see {}经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see {}经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logggr.info("Shiro开始权限认证");
        ShiroUser shiroUser = JwtUtil.parse(principals.toString());
        Set<String> roles = Sets.newHashSet();
        Set<String> perms = Sets.newHashSet();
        // 账号不存在
        if (shiroUser != null) {
            //管理员具有全部角色
            if (JwtConstants.USER_ADMIN_LOGIN_NAME.equalsIgnoreCase(shiroUser.getLoginName())) {
                roles = roleInnerService.listAll().stream().map(role -> role.getId() + "").collect(Collectors.toCollection(HashSet::new));
                perms = resourceInnerService.listAll().stream().map(resource -> resource.getPerms()).collect(Collectors.toCollection(HashSet::new));
            } else {
                // 读取用户的url和角色
                Map<String, Set<String>> resourceMap = roleInnerService.selectPermissionMapByUserId(Integer.parseInt(shiroUser.getId()));
                perms = resourceMap.get(JwtConstants.PERMS);
                roles = resourceMap.get(JwtConstants.ROLES);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.addStringPermissions(perms);
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 超级管理员拥有所有权限
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        logggr.info("拥有权限认证");
        ShiroUser user = JwtUtil.parse(principals.toString());
        return JwtConstants.USER_ADMIN_LOGIN_NAME.equals(user.getLoginName()) || super.isPermitted(principals, permission);
    }

    /**
     * 超级管理员拥有所有角色
     */
    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        logggr.info("拥有角色认证");
        ShiroUser user = JwtUtil.parse(principals.toString());
        return JwtConstants.USER_ADMIN_LOGIN_NAME.equals(user.getLoginName()) || super.hasRole(principals, roleIdentifier);
    }
}
