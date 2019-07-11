package com.cehome.cloud.user.service;

import com.cehome.cloud.user.mapper.PlatformMapper;
import com.cehome.cloud.user.model.po.Platform;
import com.cehome.cloud.user.model.query.PlatformQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 平台表 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-03-29
 */
@Service
public class PlatformInnerService {

    @Autowired
    private PlatformMapper platformMapper;

    public int add(Platform platform) {
        platform.setCreateTime(Calendar.getInstance().getTimeInMillis());
        platform.setUpdateTime(Calendar.getInstance().getTimeInMillis());
        return platformMapper.insert(platform);
    }

    public int update(Platform platform) {
        platform.setUpdateTime(Calendar.getInstance().getTimeInMillis());
        return platformMapper.update(platform);
    }

    public Platform selectById(Integer id) {
        return platformMapper.selectById(id);
    }

    public int deleteById(Integer id) {
        return platformMapper.deleteById(id);
    }

    public List<Platform> list(String name, String showName, Integer pageNo, Integer pageSize) {
        PlatformQuery platformQuery = new PlatformQuery();
        return platformMapper.selectList(platformQuery);
    }
}
