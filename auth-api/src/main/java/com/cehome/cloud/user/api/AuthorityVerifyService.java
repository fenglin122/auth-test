package com.cehome.cloud.user.api;

import com.cehome.cloud.user.UserAPI;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/10/ 9:38
 */
@FeignClient(value = UserAPI.SERVICE_ID)
public interface AuthorityVerifyService {

    /**
     * 用户是否有此url的访问权限
     * 用户信息已在token中获取，具体获取方式通过shiro框架完成
     * @param url 访问的url
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_AUTHORITY + "/isAuthority",method = RequestMethod.GET)
    Boolean isAuthority(@RequestParam(value = "url",defaultValue = "") String url);
}
