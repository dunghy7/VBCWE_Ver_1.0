package com.dtsvn.vbcwe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.dtsvn.vbcwe.dto.SearchUserInfoDTO;
import com.dtsvn.vbcwe.entity.UserInfoEntity;
import com.dtsvn.vbcwe.dto.SearchUserInfoResultDTO;

@Mapper
public interface UserInfoMapper {

    /**
     * load member
     * @param searchUserInfoDTO
     * @param pageable
     * @return
     */
    List<SearchUserInfoResultDTO> findAllUserInfo(@Param("searchUserInfoDTO")SearchUserInfoDTO searchUserInfoDTO, @Param("pageable") Pageable pageable);
    
    /**
     * count user info
     * @param searchUserInfoDTO
     * @return
     */
    Integer countUserInfo(SearchUserInfoDTO searchUserInfoDTO);
    
    /**
     * Load member by Id
     * @param email
     * @return
     */
    UserInfoEntity findOneUserInfoById(@Param("email")String email);
    
    /**
     * Update member
     * @param UserInfoEntity
     * @return
     */
    Integer updateUserInfo(UserInfoEntity userInfoEntity);
    
    /**
     * Update member
     * @param UserInfoEntity
     * @return
     */
    Integer insertUserInfo(UserInfoEntity userInfoEntity);
}
