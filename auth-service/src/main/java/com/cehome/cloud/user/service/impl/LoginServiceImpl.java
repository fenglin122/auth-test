package com.cehome.cloud.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cehome.cloud.user.api.LoginService;
import com.cehome.cloud.user.config.shiro.ShiroUser;
import com.cehome.cloud.user.config.shiro.jwt.JwtConstants;
import com.cehome.cloud.user.config.shiro.jwt.JwtUtil;
import com.cehome.cloud.user.model.po.User;
import com.cehome.cloud.user.model.request.LoginReqDto;
import com.cehome.cloud.user.service.UserInnerService;
import com.cehome.cloud.user.util.PasswordHelper;
import com.cehome.utils.exception.MicroserviceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/11/ 14:41
 */
@RestController
@Service
public class LoginServiceImpl implements LoginService {
    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UserInnerService userInnerService;

    @Override
    public User login(LoginReqDto loginReqDto) {
        try {
            User user = userInnerService.selectByLoginName(loginReqDto.getLoginName(),1);
            if (!PasswordHelper.getEncryptPassword(loginReqDto.getPassword(),user)){
                logger.error("对用户[" + loginReqDto.getLoginName() + "]进行登录验证..验证未通过,未知账户 {}");
                throw new MicroserviceException("对用户[" + loginReqDto.getLoginName() + "]进行登录验证..验证未通过,未知账户 {}");
            }
            //生成token，返回给前端
            ShiroUser shiroUser = new ShiroUser(user.getLoginName());
            shiroUser.setName(user.getName());
            shiroUser.setId(user.getId() + "");
            shiroUser.setSalt(user.getCredentialsSalt());
            loginSuccess(shiroUser,response);
            return user;
        }catch (Exception e) {
            logger.error("登陆发生未知异常,堆栈轨迹如下 {}", e);
            throw new MicroserviceException("对用户[" + loginReqDto.getLoginName() + "] 登陆发生未知异常");
        }
    }

    /**
     * 登录后更新缓存，生成token，设置响应头部信息
     *
     * @param user
     * @param response
     */
    private void loginSuccess(ShiroUser user, HttpServletResponse response) {
        //生成token
        JSONObject json = new JSONObject();
        String token = JwtUtil.createJWT(user);
        json.put("token", token);
        //写入header
        response.setHeader(JwtConstants.JWT_TOKEN, token);
        response.setHeader("Access-Control-Expose-Headers",JwtConstants.JWT_TOKEN);
    }
}
