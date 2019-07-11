package com.cehome.cloud.user.annotation;

import com.cehome.cloud.user.config.shiro.ShiroService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class RefreshFilterChainAspect {

    @Resource
    private ShiroService shiroService;

    @Pointcut("@annotation(com.cehome.cloud.user.annotation.RefreshFilterChain)")
    public void updateFilterChain() {}

    @AfterReturning("updateFilterChain()")
    public void doAfter() {
        shiroService.updateFilterChain();
    }

}
