package com.dtsvn.vbcwe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.dtsvn.vbcwe.dto.AllInputInfoDTO;
import com.dtsvn.vbcwe.dto.MemberDTO;
import com.dtsvn.vbcwe.dto.SearchReportDTO;
import com.dtsvn.vbcwe.dto.SearchReportResultDTO;
import com.dtsvn.vbcwe.entity.ReportEntity;

@Mapper
public interface MembershipMapper {

	/**
	 * Load report
	 * 
	 * @param searchReportDTO
	 * @return
	 */
	List<SearchReportResultDTO> findAllReports(@Param("searchReportDTO") SearchReportDTO searchReportDTO,
			@Param("pageable") Pageable pageable);

	/**
	 * count user info
	 * 
	 * @param searchUserInfoDTO
	 * @return
	 */
	Integer countReport(SearchReportDTO searchReportDTO);

	/**
	 * Load report by user id
	 * 
	 * @param email
	 * @return
	 */
	ReportEntity findOneReportById(@Param("email") String email);

	/**
	 * Update report
	 * 
	 * @param reportEntity
	 * @return
	 */
	Integer updateReport(ReportEntity reportEntity);
	
	/**
	 * Delete report
	 * 
	 * @param email
	 * @return
	 */
	Integer deleteReport(@Param("email") String email);

	/**
	 * 
	 * @param memberDTO
	 * @return
	 */
	Integer insertSurveyInfo(MemberDTO memberDTO);

	/**
	 * 
	 * @param memId
	 * @return
	 */
	MemberDTO loadSurveyInfo(@Param("userId") String memId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	AllInputInfoDTO getAllInputInfo(@Param("userId") String userId);
	
}
