package com.dtsvn.vbcwe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtsvn.vbcwe.dto.InCareerDTO;

@Mapper
public interface InCareerMapper {

    /**
     * get data by UserId
     * @param userId
     * @return
     */
    InCareerDTO selectByUserId(@Param("userId") String userId, @Param("tableName") String tableName);

    /**
     * insert/update data by UserId
     * @param careerCategoryDTO
     * @return
     */
    Integer processTableByUserId(InCareerDTO careerCategoryDTO);
}
