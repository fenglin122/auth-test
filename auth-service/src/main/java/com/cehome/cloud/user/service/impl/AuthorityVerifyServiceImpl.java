package com.cehome.cloud.user.service.impl;

import com.cehome.cloud.user.api.AuthorityVerifyService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/10/ 9:44
 */
@RestController
@Service
public class AuthorityVerifyServiceImpl implements AuthorityVerifyService {
    @Override
    public Boolean isAuthority(String url) {
        if ("/test/index".equals(url)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
//        return SecurityUtils.getSubject().isPermitted(url);
    }
}
