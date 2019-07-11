package com.cehome.cloud.user.api;

import com.cehome.cloud.user.UserAPI;
import com.cehome.cloud.user.model.po.Resource;
import com.cehome.cloud.user.model.request.ResourceReqDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/11/ 14:06
 */
public interface ResourceService {

    /**
     * 添加
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/add",method = RequestMethod.POST)
    public Integer add(@RequestBody ResourceReqDto resourceReqDto);

    /**
     * 更新
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/update",method = RequestMethod.POST)
    public Integer update(@RequestBody ResourceReqDto resourceReqDto);

    /**
     * 资源详情
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/getById",method = RequestMethod.GET)
    public Resource getById(Integer permission);

    /**
     * 删除资源
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/delete",method = RequestMethod.GET)
    public Integer deleteById(Integer id);

    /**
     * 资源列表
     * @param
     * @return
     */
    @RequestMapping(value = UserAPI.PATH_RESOURCE + "/listResources",method = RequestMethod.GET)
    public List<Resource> listResources() ;

}
