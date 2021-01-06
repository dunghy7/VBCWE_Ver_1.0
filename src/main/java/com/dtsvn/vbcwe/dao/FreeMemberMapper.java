package com.dtsvn.vbcwe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtsvn.vbcwe.dto.MemberDTO;

@Mapper
public interface FreeMemberMapper {
	MemberDTO loadFreeMember(@Param("userId")String userId);
	
	Integer insertSurveyInfo(MemberDTO dto);
	
	Integer updateSurveyInfo(MemberDTO dto);
	
}
