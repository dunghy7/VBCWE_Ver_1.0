package com.dtsvn.vbcwe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtsvn.vbcwe.dto.InCareerDTO;
import com.dtsvn.vbcwe.entity.InBrdCompEntity;

@Mapper
public interface InBrdCompMapper {

    /**
     * get data by UserId
     * @param userId
     * @return
     */
    InCareerDTO selectByUserId(@Param("userId") String userId);

    /**
     * insert/update data by UserId
     * @param inBrdCompEntity
     * @return
     */
    Integer processInBrdCompByUserId(InBrdCompEntity inBrdCompEntity);
}
