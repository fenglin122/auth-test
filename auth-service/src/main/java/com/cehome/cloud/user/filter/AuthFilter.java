package com.cehome.cloud.user.filter;

import com.cehome.cloud.user.config.shiro.ShiroUser;
import com.cehome.cloud.user.config.shiro.UserContext;
import com.cehome.cloud.user.config.shiro.jwt.JWTToken;
import com.cehome.cloud.user.config.shiro.jwt.JwtConstants;
import com.cehome.cloud.user.config.shiro.jwt.JwtUtil;
import com.cehome.cloud.user.service.UserInnerService;
import com.cehome.cloud.user.util.URLAnonUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter extends BasicHttpAuthenticationFilter {
	private final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static final int tokenRefreshInterval = 300;
    private UserInnerService userInnerService;

    public AuthFilter(UserInnerService userInnerService){
        this.userInnerService = userInnerService;
        this.setLoginUrl(JwtConstants.TO_LOGIN_URL);
    }

    /**
     * 检测Header里jwt_token字段
     * 判断是否登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(JwtConstants.JWT_TOKEN);
        return token != null;
    }

    /**
     * 跨域设置
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.SC_OK);
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        String token = getAuthzHeader(request);

        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);

        //绑定上下文获取账号
        ShiroUser user = JwtUtil.parse(token);

        //绑定上下文
        new UserContext(user);

        //检查是否需要更换token，需要则重新颁发
//        this.refreshTokenIfNeed(account, token, response);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                this.executeLogin(request, response);
            } catch (Exception e) {
                String msg = e.getMessage();
                Throwable throwable = e.getCause();
                if (throwable != null && throwable instanceof SignatureException) {
                    msg = "Token或者密钥不正确(" + throwable.getMessage() + ")";
                } else if (throwable != null && throwable instanceof ExpiredJwtException) {
                    msg = "Token已过期(" + throwable.getMessage() + ")";
                } else {
                    if (throwable != null) {
                        msg = throwable.getMessage();
                    }
                }
                URLAnonUtil.unauthorized(request,response,msg);
                return false;
            }
        }
        return true;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String header = httpRequest.getHeader(JwtConstants.JWT_TOKEN);
        return StringUtils.removeStart(header, "Bearer ");
    }
}
