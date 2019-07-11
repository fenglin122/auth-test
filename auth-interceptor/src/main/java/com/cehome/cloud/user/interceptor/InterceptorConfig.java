package com.cehome.cloud.user.interceptor;

import com.cehome.cloud.user.service.AuthService;
import com.cehome.cloud.user.util.URLAnonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限验证拦截器
 * Created by hyl on 2019/07/08/ 16:43
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(authService)).addPathPatterns("/**").excludePathPatterns("/error");
        super.addInterceptors(registry);
    }

    public class AuthInterceptor extends HandlerInterceptorAdapter {
        Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
        private AuthService authService;

        public AuthInterceptor(AuthService authService) {
            this.authService = authService;
        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String uri = request.getRequestURI();
            logger.info("authority isAuthority interceptor:{}",uri);
            if (!URLAnonUtil.listAnonUrl().contains(uri)) {
                if (!authService.verify(uri)) {
                    URLAnonUtil.unauthorized(request,response, "[" + uri + "] no authority");
                    return false;
                }
            }
            return true;
        }
    }
}
