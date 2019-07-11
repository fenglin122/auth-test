package com.cehome.cloud.user.config.shiro;

import com.cehome.cache.redis.RedisCacheProvider;
import com.cehome.cloud.user.config.shiro.cache.ShiroCacheManager;
import com.cehome.cloud.user.filter.AuthFilter;
import com.cehome.cloud.user.filter.SystemLogoutFilter;
import com.cehome.cloud.user.service.UserInnerService;
import com.cehome.cloud.user.util.PasswordHelper;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Map;

/*
 * 注册jwt认证过滤器
 */
@Configuration
public class ShiroConfig {

    private static final int ENCODE_LENGTH = 2;
    private static final String ENCODE_VERIFICATION = "ci/Asdo!@$%64xq(*";


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator getLifecycleBeanPostProcessor() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm, ShiroCacheManager shiroCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        securityManager.setCacheManager(shiroCacheManager);
        return securityManager;
    }

    /**
     * 设置过滤器
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager, UserInnerService userInnerService, RedisCacheProvider redisCacheProviderStr, ShiroService shiroService) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = factoryBean.getFilters();
        filterMap.put("authc", new AuthFilter(userInnerService));
        filterMap.put("logout", new SystemLogoutFilter(redisCacheProviderStr));
        factoryBean.setFilters(filterMap);
        Map<String, String> urlPermsMap = shiroService.getUrlPermsMap();
        factoryBean.setFilterChainDefinitionMap(urlPermsMap);
        return factoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.algorithmName);
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.hashIterations);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
}