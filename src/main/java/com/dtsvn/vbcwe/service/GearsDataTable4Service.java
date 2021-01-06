package com.dtsvn.vbcwe.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.dao.InCareerMapper;
import com.dtsvn.vbcwe.dto.GearsDataTable4DTO;
import com.dtsvn.vbcwe.dto.InCareerDTO;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Service
public class GearsDataTable4Service extends BaseService {

    @Autowired
    InCareerMapper inCareerMapper;

    private static String status;

    private String[] lstTable = { Constant.TABLE4_SUB1_NAME, Constant.TABLE4_SUB2_NAME, Constant.TABLE4_SUB3_NAME, Constant.TABLE4_SUB4_NAME, Constant.TABLE4_SUB5_NAME, Constant.TABLE4_SUB6_NAME,
            Constant.TABLE4_SUB7_NAME, Constant.TABLE4_SUB8_NAME, Constant.TABLE4_SUB9_NAME };

    /**
     * Lay data theo UserID
     * 
     * @return dataTable3DTOList
     */
    public List<GearsDataTable4DTO> loadDataByUserId(String tableName, String jsonTableConst) {
        Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>() {
        }.getType();
        List<GearsDataTable4DTO> dataTable4DTOList = new ArrayList<GearsDataTable4DTO>();
        // lay thong tin user
        UserEntity userEntity = commonHelper.getLoginUser();
        // lay data trong bang theo userId
        InCareerDTO careerCategoryDTO = inCareerMapper.selectByUserId(userEntity.getUserId(), tableName);
        // Trong truong hop da chua co InputData
        if (careerCategoryDTO == null || StringUtils.isEmpty(careerCategoryDTO.getInputData())) {
            // Convert json ve list
            dataTable4DTOList = gson.fromJson(jsonTableConst, dataListType);
        } else {
            dataTable4DTOList = gson.fromJson(careerCategoryDTO.getInputData(), dataListType);
            status = careerCategoryDTO.getStatus();
        }

        return dataTable4DTOList;
    }

    /**
     * 
     * @return status
     */
    public String getStatusInCarrer() {
        return status;
    }

    /**
     * Thuc hien luu data
     * 
     * @return
     */
    public void saveDataTempl(List<GearsDataTable4DTO> dataTable4DTOList, String tableName, String status) {
        String dataJson = gson.toJson(dataTable4DTOList);
        // lay thong tin user
        UserEntity userEntity = commonHelper.getLoginUser();
        // Update data table
        InCareerDTO inCareerDto = new InCareerDTO();
        inCareerDto.setTableName(tableName);
        inCareerDto.setUserId(userEntity.getUserId());
        inCareerDto.setInputData(dataJson);
        inCareerDto.setStatus(status);
        inCareerMapper.processTableByUserId(inCareerDto);
    }

    /**
     * Thực hiện validate
     * 
     * @param errors
     * @param dataTable4DtoList
     */
    public void validationInCareers(Errors errors, List<GearsDataTable4DTO> dataTable4DtoList) {
        // Check required
        for (GearsDataTable4DTO data : dataTable4DtoList) {
            if (StringUtils.isBlank(data.getTableMale().getFullTimeIndefinite())
                    || StringUtils.isBlank(data.getTableMale().getFullTimeFixedTerm())
                    || StringUtils.isBlank(data.getTableMale().getPartTimeIndefinite())
                    || StringUtils.isBlank(data.getTableMale().getTotalEnd12m())
                    || StringUtils.isBlank(data.getTableFemale().getFullTimeIndefinite())
                    || StringUtils.isBlank(data.getTableFemale().getFullTimeFixedTerm())
                    || StringUtils.isBlank(data.getTableFemale().getPartTimeIndefinite())
                    || StringUtils.isBlank(data.getTableFemale().getTotalEnd12m())) {
                            errors.rejectValue("dataTable4DtoList", MessageConstants.VALIDATE_MSG_REQUIRED,
                                    new Object[] { "" }, "");
                return;
            }
        }
        // Check maxlength
        for (GearsDataTable4DTO data : dataTable4DtoList) {
            if (data.getTableMale().getFullTimeIndefinite().length() > 7
                    || data.getTableMale().getFullTimeFixedTerm().length() > 7
                    || data.getTableMale().getPartTimeIndefinite().length() > 7
                    || data.getTableMale().getTotalEnd12m().length() > 7
                    || data.getTableFemale().getFullTimeIndefinite().length() > 7
                    || data.getTableFemale().getFullTimeFixedTerm().length() > 7
                    || data.getTableFemale().getPartTimeIndefinite().length() > 7
                    || data.getTableFemale().getTotalEnd12m().length() > 7) {
                    errors.rejectValue("dataTable4DtoList", MessageConstants.VALIDATE_MSG_MAX_LENGTH,
                            new Object[] { "7" }, "");
                return;
            }
        }
        // Check number
        for (GearsDataTable4DTO data : dataTable4DtoList) {
            if (!data.getTableMale().getFullTimeIndefinite().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTableMale().getFullTimeFixedTerm().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTableMale().getPartTimeIndefinite().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTableMale().getTotalEnd12m().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTableFemale().getFullTimeIndefinite().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTableFemale().getFullTimeFixedTerm().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTableFemale().getPartTimeIndefinite().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTableFemale().getTotalEnd12m().matches(Constant.REGEX_ONLY_NUMBER)) {
                errors.rejectValue("dataTable4DtoList", MessageConstants.VALIDATE_MSG_NUMBER, new Object[] { "0" }, "");
                return;
            }
        }
    }

    /**
     * Load data for chart 9
     * 
     * @param userId
     * @return
     */
    public float[][] getChartData4Slide9(String userId) {
        float[][] chart9Data = new float[9][2];
        try {
            for (int i = 0; i < lstTable.length; i++) {
                InCareerDTO empl = inCareerMapper.selectByUserId(userId, lstTable[i]);
                if (empl == null)
                    continue;

                String jsonData = empl.getInputData();
                if (StringUtils.isBlank(jsonData))
                    continue;

                // Convert json to object
                Type userListType = new TypeToken<ArrayList<GearsDataTable4DTO>>() {
                }.getType();
                List<GearsDataTable4DTO> dataTable4DTOList = gson.fromJson(jsonData, userListType);
                if (dataTable4DTOList == null || dataTable4DTOList.isEmpty())
                    continue;

                // Filter main position
                dataTable4DTOList = dataTable4DTOList.stream().filter(x -> x.getParentLevel().isEmpty()).collect(Collectors.toList());
                if (dataTable4DTOList == null || dataTable4DTOList.isEmpty())
                    continue;

                // Calculate staff by position and gender
                chart9Data[i][0] = dataTable4DTOList.stream().mapToInt(z -> Integer.valueOf(z.getTableFemale().getTotalEnd12m())).sum();
                chart9Data[i][1] = dataTable4DTOList.stream().mapToInt(z -> Integer.valueOf(z.getTableMale().getTotalEnd12m())).sum();
            }

            // Recalculate percentage
            for (float[] dataRow : chart9Data) {
                float sumData = dataRow[0] + dataRow[1];
                if (sumData == 0) {
                    dataRow[0] = 0;
                    dataRow[1] = 0;
                    continue;
                }

                dataRow[0] = (float) Math.round(dataRow[0] / sumData * 100) / 100;
                dataRow[1] = (float) Math.round(dataRow[1] / sumData * 100) / 100;
            }
        } catch (JsonSyntaxException e) {
            // Exception
            e.printStackTrace();
        }
        return chart9Data;
    }

    /**
     * Load data for chart 10
     * 
     * @param userId
     * @return
     */
    public float[][] getChartData4Slide10(String userId) {
        float[][] chart9Data = new float[40][2];
        List<float[]> lstData = new ArrayList<>();
        try {
            for (int i = 0; i < lstTable.length; i++) {
                InCareerDTO empl = inCareerMapper.selectByUserId(userId, lstTable[i]);
                if (empl == null)
                    continue;

                String jsonData = empl.getInputData();
                if (StringUtils.isBlank(jsonData))
                    continue;

                // Convert json to object
                Type userListType = new TypeToken<ArrayList<GearsDataTable4DTO>>() {
                }.getType();
                List<GearsDataTable4DTO> dataTable4DTOList = gson.fromJson(jsonData, userListType);
                if (dataTable4DTOList == null || dataTable4DTOList.isEmpty())
                    continue;

                // Filter main position
                dataTable4DTOList = dataTable4DTOList.stream().filter(x -> x.getParentLevel().isEmpty()).collect(Collectors.toList());
                if (dataTable4DTOList == null || dataTable4DTOList.isEmpty())
                    continue;

                // Calculate staff by position and gender
                for (GearsDataTable4DTO gearsDataTable4DTO : dataTable4DTOList) {
                    float[] tmpData = new float[2];
                    tmpData[0] = Integer.valueOf(gearsDataTable4DTO.getTableFemale().getTotalEnd12m());
                    tmpData[1] = Integer.valueOf(gearsDataTable4DTO.getTableMale().getTotalEnd12m());
                    lstData.add(tmpData);
                }
            }

            // Recalculate percentage
            for (int i = 0; i < lstData.size(); i++) {
                float sumData = lstData.get(i)[0] + lstData.get(i)[1];
                if (sumData == 0) {
                    chart9Data[i][0] = 0;
                    chart9Data[i][1] = 0;
                    continue;
                }

                chart9Data[i][0] = (float) Math.round(lstData.get(i)[0] / sumData * 100) / 100;
                chart9Data[i][1] = (float) Math.round(lstData.get(i)[1] / sumData * 100) / 100;
            }
        } catch (JsonSyntaxException e) {
            // Exception
            e.printStackTrace();
        }
        return chart9Data;
    }
}
