package com.cehome.cloud.user.service.impl;

import com.cehome.cloud.common.object.BaseResult;
import com.cehome.cloud.user.api.UserService;
import com.cehome.cloud.user.model.request.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/08/ 17:00
 */
@RestController
@Service
public class UserServiceImpl implements UserService {
    @Override
    public BaseResult login(@RequestBody UserRequest userRequest) {
        return BaseResult.success() ;
    }

    @Override
    public Boolean info(String info) {
        if ("success".equals(info)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
