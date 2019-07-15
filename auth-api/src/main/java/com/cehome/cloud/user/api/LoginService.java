package com.cehome.cloud.user.api;

import com.cehome.cloud.user.UserAPI;
import com.cehome.cloud.user.model.po.User;
import com.cehome.cloud.user.model.request.LoginReqDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/11/ 14:07
 */
@FeignClient(value = UserAPI.SERVICE_ID)
public interface LoginService {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    User login(@RequestBody LoginReqDto loginReqDto);
}
