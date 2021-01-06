package com.dtsvn.vbcwe.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.dao.InBrdCompMapper;
import com.dtsvn.vbcwe.dto.GearsDataTable3DTO;
import com.dtsvn.vbcwe.dto.InCareerDTO;
import com.dtsvn.vbcwe.entity.InBrdCompEntity;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.google.gson.reflect.TypeToken;

@Service
public class GearsDataTable3Service extends BaseService {

	@Autowired
	InBrdCompMapper inBrdCompMapper;

	// status của bảng in_brd_comp
    private static String status;
    
	/**
	 * Lay data theo UserID
	 * 
	 * @return dataTable3DTOList
	 */
	public List<GearsDataTable3DTO> loadDataByUserId() {
		Type dataListType = new TypeToken<ArrayList<GearsDataTable3DTO>>() {
		}.getType();
		List<GearsDataTable3DTO> dataTable3DTOList = new ArrayList<GearsDataTable3DTO>();
		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();
		// lay data trong bang [IN_EMPL_NVMT] theo userId
		InCareerDTO inBrdComp = inBrdCompMapper.selectByUserId(userEntity.getUserId());
		// Trong truong hop da chua co InputData
		if (inBrdComp == null || StringUtils.isEmpty(inBrdComp.getInputData())) {
			// Convert json ve list
			dataTable3DTOList = gson.fromJson(Constant.JSON_TEMPL.TABLE3, dataListType);
		} else {
			dataTable3DTOList = gson.fromJson(inBrdComp.getInputData(), dataListType);
			status = inBrdComp.getStatus();
		}

		return dataTable3DTOList;
	}
	
	/**
     * Lay in_brd_comp.status
     * 
     * @return status
     */
    public String getStatusInBrdComp() {
        return status;
    }

	/**
	 * Thuc hien luu data
	 * 
	 * @return
	 */
	public void saveDataTempl(List<GearsDataTable3DTO> dataTable3DTOList, String status) {
		String dataJson = gson.toJson(dataTable3DTOList);
		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();
		// Update data table IN_EMPL_NVMT
		InBrdCompEntity inBrdCompEntity = new InBrdCompEntity();
		inBrdCompEntity.setInputData(dataJson);
		inBrdCompEntity.setUserId(userEntity.getUserId());
		inBrdCompEntity.setStatus(status);
		inBrdCompMapper.processInBrdCompByUserId(inBrdCompEntity);
	}
}
