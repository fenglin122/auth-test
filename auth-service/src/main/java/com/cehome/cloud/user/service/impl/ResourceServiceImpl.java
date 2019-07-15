package com.cehome.cloud.user.service.impl;

import com.cehome.cloud.user.annotation.RefreshFilterChain;
import com.cehome.cloud.user.api.ResourceService;
import com.cehome.cloud.user.model.po.Resource;
import com.cehome.cloud.user.model.request.ResourceReqDto;
import com.cehome.cloud.user.service.ResourceInnerService;
import com.cehome.cloud.user.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/11/ 15:46
 */
@RestController
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceInnerService resourceInnerService;


    @RefreshFilterChain
    public Integer add(@RequestBody ResourceReqDto resourceReqDto) {
        Resource resource = BeanUtils.copy(resourceReqDto,Resource.class);
        return resourceInnerService.add(resource);
    }


    @RefreshFilterChain
    public Integer update(@RequestBody ResourceReqDto resourceReqDto) {
        Resource resource = BeanUtils.copy(resourceReqDto,Resource.class);
        return resourceInnerService.update(resource);
    }

    public Resource getById(@RequestParam("id") Integer id) {
        return resourceInnerService.selectById(id);
    }

    @RefreshFilterChain
    public Integer deleteById(@RequestParam("id") Integer id) {
        return resourceInnerService.deleteById(id);
    }

    public List<Resource> listResources() {
        return resourceInnerService.listAll();
    }
}
