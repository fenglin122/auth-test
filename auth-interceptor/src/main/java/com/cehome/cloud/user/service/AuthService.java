package com.cehome.cloud.user.service;

import com.cehome.cloud.user.api.AuthorityVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 验证请求是否有权限
 * Created by hyl on 2019/07/09/ 19:51
 */
@Service
public class AuthService {

    //使用懒加载，防止循环引用
    @Autowired
    @Lazy
    private AuthorityVerifyService authorityVerifyService;

    /**
     * 调用微服务的权限验证接口验证请求的访问权限
     * @param url 请求url地址
     * @return
     */
    public Boolean verify(String url){
        return authorityVerifyService.isAuthority(url);
    }
}
