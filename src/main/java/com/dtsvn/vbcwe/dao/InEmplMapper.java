package com.dtsvn.vbcwe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtsvn.vbcwe.dto.InCareerDTO;
import com.dtsvn.vbcwe.entity.InEmplEntity;

@Mapper
public interface InEmplMapper {

    /**
     * get data by UserId
     * @param userId
     * @return
     */
    InCareerDTO selectByUserId(@Param("userId") String userId);

    /**
     * insert/update data by UserId
     * @param InEmplEntity
     * @return
     */
    Integer processInEmplByUserId(InEmplEntity inEmplEntity);
}
