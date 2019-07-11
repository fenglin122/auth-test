package com.cehome.cloud.user.mapper;

import com.cehome.cloud.user.model.po.Platform;
import com.cehome.cloud.user.model.query.PlatformQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatformMapper extends BaseMapper {
    int deleteById(@Param("id") Integer id);

    int insert(@Param("platform") Platform record);

    Platform selectById(@Param("id") Integer id);

    int update(@Param("platform") Platform record);

    List<Platform> selectList(@Param("query") PlatformQuery query);

}