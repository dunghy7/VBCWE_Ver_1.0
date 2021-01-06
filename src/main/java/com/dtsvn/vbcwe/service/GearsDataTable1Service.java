package com.dtsvn.vbcwe.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.dao.InEmplNvmtMapper;
import com.dtsvn.vbcwe.dto.GearsDataTable1DTO;
import com.dtsvn.vbcwe.dto.InCareerDTO;
import com.dtsvn.vbcwe.entity.InEmplNvmtEntity;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.repository.InEmplNvmtRepository;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Service
public class GearsDataTable1Service extends BaseService {

    @Autowired
    InEmplNvmtMapper inEmplNvmtMapper;

    @Autowired
    InEmplNvmtRepository inEmplNvmtRepository;
    
    // status của bảng in_empl_nvmt
    private static String status;

    /**
     * Lay data theo UserID
     * 
     * @return dataTable1DTOList
     */
    public List<GearsDataTable1DTO> loadDataByUserId() {
        Type userListType = new TypeToken<ArrayList<GearsDataTable1DTO>>() {
        }.getType();
        List<GearsDataTable1DTO> dataTable1DTOList = new ArrayList<>();
        // lay thong tin user
        UserEntity userEntity = commonHelper.getLoginUser();
        // lay data trong bang [IN_EMPL_NVMT] theo userId
        InCareerDTO emplNvmt = inEmplNvmtMapper.selectByUserId(userEntity.getUserId());
        // Trong truong hop da chua co InputData
        if (emplNvmt == null || StringUtils.isEmpty(emplNvmt.getInputData())) {
            // Convert json ve list
            dataTable1DTOList = gson.fromJson(Constant.JSON_TEMPL.TABLE1, userListType);
        } else {
            dataTable1DTOList = gson.fromJson(emplNvmt.getInputData(), userListType);
            status = emplNvmt.getStatus();
        }
        return dataTable1DTOList;
    }

    /**
     * Lay in_empl_nvmt.status
     * 
     * @return status
     */
    public String getStatusInEmplNvmt() {
        return status;
    }

    /**
     * Thuc hien luu data
     * 
     * @return
     */
    public void saveDataTempl(List<GearsDataTable1DTO> dataTable1DTOList, String status) {
        String dataJson = gson.toJson(dataTable1DTOList);
        // lay thong tin user
        UserEntity userEntity = commonHelper.getLoginUser();
        // Update data table IN_EMPL_NVMT
        InEmplNvmtEntity inEmplNvmtEntity = new InEmplNvmtEntity();
        inEmplNvmtEntity.setInputData(dataJson);
        inEmplNvmtEntity.setUserId(userEntity.getUserId());
        inEmplNvmtEntity.setStatus(status);
        inEmplNvmtMapper.processInEmplNvmtByUserId(inEmplNvmtEntity);
    }

    /**
     * Load data for chart 11 and 12
     * @param userId
     * @return
     */
    public float[][] getChartData4Slide11_12(String userId, String slideNumber) {
        float[][] chart11Data = new float[6][2];
        try {
            InCareerDTO inEmplNvmt = inEmplNvmtMapper.selectByUserId(userId);
            if (inEmplNvmt == null)
                return chart11Data;
            
            String jsonData = inEmplNvmt.getInputData();
            if (StringUtils.isBlank(jsonData))
                return chart11Data;
            
            // Convert json to object
            Type userListType = new TypeToken<ArrayList<GearsDataTable1DTO>>() {}.getType();
            List<GearsDataTable1DTO> dataTable1DTOList = gson.fromJson(jsonData, userListType);
            if (dataTable1DTOList == null || dataTable1DTOList.isEmpty())
                return chart11Data;
            
            // Assign value to array
            float value = 0;
            String gender;
            Integer level;
            for (int i = 0; i < dataTable1DTOList.size(); i++) {
                GearsDataTable1DTO gearsDataTable1DTO = dataTable1DTOList.get(i);
                if ("11".equals(slideNumber)) {
                    value = Float.parseFloat(gearsDataTable1DTO.getPromotedFromLvl());
                } else if ("12".equals(slideNumber)) {
                    value = Float.parseFloat(gearsDataTable1DTO.getRecruitedOutside());
                }
                gender= gearsDataTable1DTO.getGender();
                level = Integer.parseInt(gearsDataTable1DTO.getLevel());
                
                //Total
                if (level == 0) {
                    if (Constant.GENDER_FEMALE.equals(gender)) {
                        chart11Data[5][0] = value;
                    } else if (Constant.GENDER_MALE.equals(gender)) {
                        chart11Data[5][1] = value;
                    }
                    continue;
                }
                
                //Level
                if (Constant.GENDER_FEMALE.equals(gender)) {
                    chart11Data[level - 1][0] = value;
                } else if (Constant.GENDER_MALE.equals(gender)) {
                    chart11Data[level - 1][1] = value;
                }
            }
            
            //Recalculate percentage
            for (float[] dataRow : chart11Data) {
                float sumData = dataRow[0] + dataRow[1];
                if (sumData == 0) {
                    dataRow[0] = 0;
                    dataRow[1] = 0;
                    continue;
                }
                
                dataRow[0] = (float)Math.round(dataRow[0]/sumData * 100) / 100;
                dataRow[1] = (float)Math.round(dataRow[1]/sumData * 100) / 100;
            }
            
        } catch (JsonSyntaxException e) {
            // Exception
            e.printStackTrace();
        }
        return chart11Data;
    }

	/**
	 * Load data for chart 13
	 * 
	 * @param userId
	 * @return
	 */
	public float[][] getChartData4Slide13(String userId) {
		float[][] chart13Data = new float[5][2];
		float[][] chart13LeftData = new float[5][2];
		float[][] chart13BeginData = new float[5][2];
		try {
			InEmplNvmtEntity inEmplNvmt = inEmplNvmtRepository.findByUserId(userId);
			if (inEmplNvmt == null) {
				return chart13Data;
			}

			String jsonData = inEmplNvmt.getInputData();
			if (StringUtils.isBlank(jsonData)) {
				return chart13Data;
			}

			// Convert json to object
			Type userListType = new TypeToken<ArrayList<GearsDataTable1DTO>>() {
			}.getType();
			List<GearsDataTable1DTO> dataTable1DTOList = gson.fromJson(jsonData, userListType);
			if (dataTable1DTOList == null || dataTable1DTOList.isEmpty()) {
				return chart13Data;
			}
			// Assign value to array
			float leftValue = 0;
			float beginValue = 0;
			String gender;
			Integer level;
			for (int i = 0; i < dataTable1DTOList.size(); i++) {
				GearsDataTable1DTO gearsDataTable1DTO = dataTable1DTOList.get(i);
				leftValue = Float.parseFloat(gearsDataTable1DTO.getLeftCompany());
				beginValue = Float.parseFloat(gearsDataTable1DTO.getTotalBegin12m());
				gender = gearsDataTable1DTO.getGender();
				level = Integer.parseInt(gearsDataTable1DTO.getLevel());
				// Không có cột tính tổng nên bỏ qua level 0
				if (level > 0) {
					// Level
					if (Constant.GENDER_FEMALE.equals(gender)) {
						chart13LeftData[level - 1][0] = leftValue;
						chart13BeginData[level - 1][0] = beginValue;
					} else if (Constant.GENDER_MALE.equals(gender)) {
						chart13LeftData[level - 1][1] = leftValue;
						chart13BeginData[level - 1][1] = beginValue;
					}
				}
			}

			// Recalculate percentage
			for (int i = 0; i < chart13Data.length; i++) {
				chart13Data[i][0] = (float)(100 - Math.round(chart13LeftData[i][0] / chart13BeginData[i][0] * 100)) / 100 ;
				chart13Data[i][1] = (float)(100 - Math.round(chart13LeftData[i][1] / chart13BeginData[i][1] * 100)) / 100;
			}
		} catch (JsonSyntaxException e) {
			// Exception
			e.printStackTrace();
		}
		return chart13Data;
	}
}
