package com.cehome.cloud.user.api;

import com.cehome.cloud.user.UserAPI;
import com.cehome.cloud.user.model.po.Resource;
import com.cehome.cloud.user.model.request.ResourceReqDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/11/ 14:06
 */
@FeignClient(value = UserAPI.SERVICE_ID)
public interface ResourceService {

    /**
     * 添加
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/add",method = RequestMethod.POST)
    Integer add(@RequestBody ResourceReqDto resourceReqDto);

    /**
     * 更新
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/update",method = RequestMethod.POST)
    Integer update(@RequestBody ResourceReqDto resourceReqDto);

    /**
     * 资源详情
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/getById",method = RequestMethod.GET)
    Resource getById(@RequestParam("id") Integer id);

    /**
     * 删除资源
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/delete",method = RequestMethod.GET)
    Integer deleteById(@RequestParam("id") Integer id);

    /**
     * 资源列表
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/listResources",method = RequestMethod.GET)
    List<Resource> listResources() ;

}
