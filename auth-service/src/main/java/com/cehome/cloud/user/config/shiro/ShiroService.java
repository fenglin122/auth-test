package com.cehome.cloud.user.config.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ShiroService {
    private static final Logger log = LoggerFactory.getLogger(ShiroService.class);

    @Autowired
    private AuthorityUpdateService authorityUpdateService;
    private AtomicLong updateTag = new AtomicLong(1);

    /**
     * 更新 Shiro 过滤器链
     */
    public void updateFilterChain() {
        authorityUpdateService.sendPermsUpdateMessage(updateTag.incrementAndGet());
    }

    /**
     * 从数据库加载用户拥有的菜单权限和 API 权限.
     */
    public Map<String, String> getUrlPermsMap() {
        return authorityUpdateService.getUrlPermsMap();
    }
}