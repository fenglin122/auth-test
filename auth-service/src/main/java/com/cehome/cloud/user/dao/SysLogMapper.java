package com.cehome.cloud.user.dao;


import com.cehome.cloud.user.model.po.SysLog;

public interface SysLogMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}