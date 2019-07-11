package com.cehome.cloud.user.mapper;

import com.cehome.cloud.user.model.po.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {

    int deleteById(@Param("id") Integer id);

    int insert(@Param("resource") Resource resource);

    Resource selectById(Integer id);

    int update(@Param("resource") Resource resource);

    List<Resource> selectByPid(@Param("pid") Integer pid);

    List<Resource> listAll();
}