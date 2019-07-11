package com.cehome.cloud.user.service;

import com.cehome.cloud.user.mapper.ResourceMapper;
import com.cehome.cloud.user.model.po.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-03-29
 */
@Service
public class ResourceInnerService {

    @Autowired
    private ResourceMapper resourceMapper;

    public Resource selectById(Integer id) {
        return resourceMapper.selectById(id);
    }

    public int add(Resource resource) {
        resource.setCreateTime(Calendar.getInstance().getTime());
        resource.setUpdateTime(Calendar.getInstance().getTime());
        return resourceMapper.insert(resource);
    }

    public int update(Resource resource) {
        resource.setUpdateTime(Calendar.getInstance().getTime());
        return resourceMapper.update(resource);
    }

    public int deleteById(Integer id) {
        return resourceMapper.deleteById(id);
    }

    public List<Resource> selectByPid(Integer pid) {
        return resourceMapper.selectByPid(pid);
    }

    public List<Resource> listAll() {
        return resourceMapper.listAll();
    }
}
