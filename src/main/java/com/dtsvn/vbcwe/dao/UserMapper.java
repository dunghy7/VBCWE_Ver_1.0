package com.dtsvn.vbcwe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtsvn.vbcwe.dao.common.BaseMapper;
import com.dtsvn.vbcwe.entity.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * do login
     * @param userId
     * @return
     */
    UserEntity doLogin(@Param("userId") String userId);

    /**
     * insert user login
     * @param userEntity
     * @return
     */
    Integer insertUser(UserEntity userEntity);
    
    /**
     * update password
     * @param userEntity
     * @return
     */
    Integer updatePassword(UserEntity userEntity);
}
