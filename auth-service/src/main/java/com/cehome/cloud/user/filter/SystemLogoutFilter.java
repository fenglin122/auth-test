package com.cehome.cloud.user.filter;

import com.alibaba.fastjson.JSON;
import com.cehome.cache.CacheKey;
import com.cehome.cache.redis.RedisCacheProvider;
import com.cehome.cloud.common.object.BaseResult;
import com.cehome.cloud.user.config.shiro.UserContext;
import com.cehome.cloud.user.config.shiro.jwt.JwtConstants;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 人生走出去的每一步，都算数。
 */
public class SystemLogoutFilter extends LogoutFilter {

    private RedisCacheProvider redisCacheProvider;

    public SystemLogoutFilter(RedisCacheProvider redisCacheProvider) {
        this.redisCacheProvider = redisCacheProvider;
    }

    private static final Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        try {
            if(UserContext.getCurrentUser()!=null){
                String account = UserContext.getCurrentUser().getLoginName();
                if(!StringUtils.isEmpty(account)){
                    // 清除可能存在的Shiro权限信息缓存
                    redisCacheProvider.remove(new CacheKey(JwtConstants.CACHE_NAMESPACE,JwtConstants.LOGIN_TOKEN,account));
                }
            }
            subject.logout();
        } catch (Exception ex) {
            logger.error("退出登录错误",ex);
        }

        this.writeResult(response);
        //不执行后续的过滤器
        return false;
    }

    private void writeResult(ServletResponse response){
        //响应Json结果
        try(PrintWriter out = response.getWriter()){
            out.write(JSON.toJSONString(BaseResult.success()));
        }catch (IOException e){
            logger.error("返回Response信息出现IOException异常" ,e);
        }
    }
}
