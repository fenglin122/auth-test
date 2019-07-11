package com.cehome.cloud.user.config.shiro.cache;

import com.cehome.cache.redis.RedisCacheProvider;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现shiro框架的缓存管理，使用自定义的缓存实现方式缓存数据
 * Created by hyl on 2019/07/10/ 12:22
 */
@Service
public class ShiroCacheManager implements CacheManager {

    @Resource(name = "redisCacheProviderStr")
    private RedisCacheProvider redisCacheProviderStr;


    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroCache<>(redisCacheProviderStr);
    }
}
