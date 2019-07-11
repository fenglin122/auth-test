package com.cehome.cloud.user.config.shiro.cache;

import com.cehome.cache.CacheKey;
import com.cehome.cache.redis.RedisCacheProvider;
import com.cehome.cloud.user.config.shiro.jwt.JwtConstants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * shiro框架缓存配置，使用redis存储缓存数据
 * Created by hyl on 2019/07/10/ 11:12
 */
public class ShiroCache<K,V> implements Cache<K,V> {

    private RedisCacheProvider redisCacheProvider;

    public ShiroCache(RedisCacheProvider redisCacheProvider){
        this.redisCacheProvider = redisCacheProvider;
    }
    @Override
    public Object get(Object k) throws CacheException {
        CacheKey cacheKey = getCacheKey(k);
        return redisCacheProvider.get(cacheKey);
    }

    @Override
    public Object put(Object k, Object v) throws CacheException {
        CacheKey cacheKey = getCacheKey(k);
        redisCacheProvider.put(cacheKey,v);
        return v;
    }

    @Override
    public Boolean remove(Object k) throws CacheException {
        CacheKey cacheKey = getCacheKey(k);
        return redisCacheProvider.remove(cacheKey);
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 20;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private CacheKey getCacheKey(Object key){
        return new CacheKey(JwtConstants.CACHE_NAMESPACE, JwtConstants.LOGIN_TOKEN,key.toString());
    }
}