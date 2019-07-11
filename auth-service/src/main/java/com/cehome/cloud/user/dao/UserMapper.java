package com.cehome.cloud.user.dao;

import com.cehome.cloud.user.model.po.User;
import com.cehome.cloud.user.model.query.SearchUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends BaseMapper {
    int deleteById(@Param("id") Integer id);

    int insert(@Param("user") User record);

    User selectById(@Param("id") Integer id);

    int update(@Param("user") User record);

    User selectByLoginName(@Param("loginName") String loginName, @Param("platformId") Integer platformId);

    User selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password, @Param("platformId") Integer platformId);

    List<User> selectList(@Param("query") SearchUserQuery userQuery);

    int selectCount(@Param("query") SearchUserQuery userQuery);

    int validate(@Param("loginName") String loginName);
}