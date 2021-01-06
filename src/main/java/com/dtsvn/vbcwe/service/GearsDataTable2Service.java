package com.dtsvn.vbcwe.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.dao.BenchmarkMapper;
import com.dtsvn.vbcwe.dao.InEmplMapper;
import com.dtsvn.vbcwe.dao.InEmplNvmtMapper;
import com.dtsvn.vbcwe.dto.BenchmarkDTO;
import com.dtsvn.vbcwe.dto.GearsDataTable1DTO;
import com.dtsvn.vbcwe.dto.GearsDataTable2DTO;
import com.dtsvn.vbcwe.dto.InCareerDTO;
import com.dtsvn.vbcwe.entity.InEmplEntity;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Service
public class GearsDataTable2Service extends BaseService {

	@Autowired
	InEmplMapper inEmplMapper;

	@Autowired
	InEmplNvmtMapper inEmplNvmtMapper;

    @Autowired
    BenchmarkMapper benchmarkMapper;
    
    // status của bảng in_empl
    private static String status;

	/**
	 * Lay data theo UserID
	 * 
	 * @return dataTable2DTOList
	 */
	public List<GearsDataTable2DTO> loadDataByUserId() {
		Type dataListType = new TypeToken<ArrayList<GearsDataTable2DTO>>() {
		}.getType();
		List<GearsDataTable2DTO> dataTable2DTOList = new ArrayList<GearsDataTable2DTO>();
		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();
		// lay data trong bang [IN_EMPL_NVMT] theo userId
		InCareerDTO empl = inEmplMapper.selectByUserId(userEntity.getUserId());
		// Trong truong hop da chua co InputData
		if (empl == null || StringUtils.isEmpty(empl.getInputData())) {
			// Convert json ve list
			dataTable2DTOList = gson.fromJson(Constant.JSON_TEMPL.TABLE2, dataListType);
		} else {
			dataTable2DTOList = gson.fromJson(empl.getInputData(), dataListType);
			status = empl.getStatus();
		}

		// lay data trong bang [IN_EMPL_NVMT] theo userId
		InCareerDTO emplNvmt = inEmplNvmtMapper.selectByUserId(userEntity.getUserId());
		// Lấy data tại cột [Tổng số nhân viên cuối kỳ tham chiếu 12 tháng] trong bảng
		// Dịch chuyển lao động
		if (emplNvmt != null && StringUtils.isNotEmpty(emplNvmt.getInputData())) {
			Type table1Type = new TypeToken<ArrayList<GearsDataTable1DTO>>() {
			}.getType();
			List<GearsDataTable1DTO> dataTable1DTOList = gson.fromJson(emplNvmt.getInputData(), table1Type);

			// Đưa value của [Tổng số nhân viên cuối kỳ tham chiếu 12 tháng] vào cột [Kiểm
			// tra dữ liệu]
			for (GearsDataTable2DTO dataTable2DTO : dataTable2DTOList) {
				for (GearsDataTable1DTO dataTable1DTO : dataTable1DTOList) {
					if (StringUtils.equals(dataTable2DTO.getLevel(), dataTable1DTO.getLevel())
							&& StringUtils.equals(dataTable2DTO.getGender(), dataTable1DTO.getGender())) {
						dataTable2DTO.setCheckAgain(dataTable1DTO.getTotalEnd12m());
					}
				}
			}
		}
		return dataTable2DTOList;
	}
	
	/**
     * Lay in_empl.status
     * 
     * @return status
     */
    public String getStatusInEmpl() {
        return status;
    }

	/**
	 * Thuc hien luu data
	 * 
	 * @return
	 */
	public void saveDataTempl(List<GearsDataTable2DTO> dataTable2DTOList, String status) {
		String dataJson = gson.toJson(dataTable2DTOList);
		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();
		// Update data table IN_EMPL_NVMT
		InEmplEntity inEmplEntity = new InEmplEntity();
		inEmplEntity.setInputData(dataJson);
		inEmplEntity.setUserId(userEntity.getUserId());
		inEmplEntity.setStatus(status);
		inEmplMapper.processInEmplByUserId(inEmplEntity);
	}
	
	/**
	 * Load data for chart 8
	 * 
	 * @param userId
	 * @return
	 */
	public float[][] getChartData4Slide8(String userId) {
		float[][] chart8Data = new float[6][2];
        try {
        	InCareerDTO empl = inEmplMapper.selectByUserId(userId);
            if (empl == null)
                return chart8Data;
            
            String jsonData = empl.getInputData();
            if (StringUtils.isBlank(jsonData))
                return chart8Data;
            
            // Convert json to object
            Type userListType = new TypeToken<ArrayList<GearsDataTable2DTO>>() {}.getType();
            List<GearsDataTable2DTO> dataTable2DTOList = gson.fromJson(jsonData, userListType);
            if (dataTable2DTOList == null || dataTable2DTOList.isEmpty())
                return chart8Data;
            
            // Assign value to array
            float value = 0;
            String gender;
            Integer level;
            for (int i = 0; i < dataTable2DTOList.size(); i++) {
            	GearsDataTable2DTO gearsDataTable2DTO = dataTable2DTOList.get(i);
            	value = Float.parseFloat(gearsDataTable2DTO.getTotal());
                gender= gearsDataTable2DTO.getGender();
                level = Integer.parseInt(gearsDataTable2DTO.getLevel());
                
                //Assign value to arrays
                if (Constant.GENDER_FEMALE.equals(gender)) {
                	chart8Data[level][0] = value;
                } else if (Constant.GENDER_MALE.equals(gender)) {
                	chart8Data[level][1] = value;
                }
            }
            
            //Recalculate percentage
            for (float[] dataRow : chart8Data) {
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
        return chart8Data;
	}

    /**
     * load data for slide 14
     * 
     * @param userId
     * @return
     */
    public float[][] getChartData4Slide14(String userId) {
        float[][] chart14Data = new float[6][4];
        try {
            float[][] chart8Data = getChartData4Slide8(userId);
            String benchmarkData = benchmarkMapper.findAllBenchmark();
            if (StringUtils.isBlank(benchmarkData)) {
                return chart14Data;
            }

            // Convert json to object
            Type userListType = new TypeToken<ArrayList<BenchmarkDTO>>() {
            }.getType();
            List<BenchmarkDTO> benchmarkDTOList = gson.fromJson(benchmarkData, userListType);
            if (benchmarkDTOList == null || benchmarkDTOList.isEmpty())
                return chart14Data;

            // Add value to array
            for (int i = 0; i < chart8Data.length; i++) {
                chart14Data[i][0] = Math.round(chart8Data[i][0] * 100);
                chart14Data[i][1] = Float.valueOf(benchmarkDTOList.get(i).getAverageWomen());
                chart14Data[i][2] = Float.valueOf(benchmarkDTOList.get(i).getMinDiff());
                chart14Data[i][3] = Float.valueOf(benchmarkDTOList.get(i).getMaxDiff());
            }

        } catch (JsonSyntaxException e) {
            // Exception
            e.printStackTrace();
        }
        return chart14Data;
    }
}
