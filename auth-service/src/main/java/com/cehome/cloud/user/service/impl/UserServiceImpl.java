package com.cehome.cloud.user.service.impl;

import com.cehome.cloud.user.api.UserService;
import com.cehome.cloud.user.model.request.UserReqDto;
import com.cehome.cloud.user.service.UserInnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: semaphore
 * Created by hyl on 2019/07/08/ 17:00
 */
@RestController
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInnerService userInnerService;

    @Override
    public Integer add(@RequestBody UserReqDto userReqDto) {
        return userInnerService.add(userReqDto);
    }

    @Override
    public Integer update(@RequestBody UserReqDto userReqDto) {
        return userInnerService.update(userReqDto);
    }
}
