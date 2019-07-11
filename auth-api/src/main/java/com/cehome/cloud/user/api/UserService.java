package com.cehome.cloud.user.api;

import com.cehome.cloud.common.object.BaseResult;
import com.cehome.cloud.user.UserAPI;
import com.cehome.cloud.user.model.request.UserReqDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/08/ 14:10
 */
@FeignClient(value = UserAPI.SERVICE_ID)
public interface UserService {

    @RequestMapping(value = UserAPI.PATH_USER + "/login",method = RequestMethod.POST)
    public BaseResult login(@RequestBody UserReqDto userReqDto);

    @RequestMapping(value = UserAPI.PATH_USER + "/info",method = RequestMethod.GET)
    public Boolean info(@RequestParam(value = "info",defaultValue = "") String info);
}
