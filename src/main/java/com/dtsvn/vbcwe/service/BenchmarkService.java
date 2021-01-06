package com.dtsvn.vbcwe.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.common.exception.RollBackException;
import com.dtsvn.vbcwe.dao.BenchmarkMapper;
import com.dtsvn.vbcwe.dto.BenchmarkDTO;
import com.dtsvn.vbcwe.entity.BenchmarkEntity;
import com.google.gson.reflect.TypeToken;

@Service
public class BenchmarkService extends BaseService {

    @Autowired
    BenchmarkMapper benchmarkMapper;

    public List<BenchmarkDTO> findAllBenchmark() {
        Type userListType = new TypeToken<ArrayList<BenchmarkDTO>>() {
        }.getType();
        List<BenchmarkDTO> benchmarkDTOList = new ArrayList<>();
        // lay data
        String data = benchmarkMapper.findAllBenchmark();
        // Trong truong hop da chua co InputData
        if (StringUtils.isBlank(data)) {
            // Convert json ve list
        	benchmarkDTOList = gson.fromJson(Constant.JSON_TEMPL.BENCHMARK, userListType);
        } else {
        	benchmarkDTOList = gson.fromJson(data, userListType);
        }
        return benchmarkDTOList;
    }
    
    @Transactional(rollbackFor = RollBackException.class)
    public void updateBenchmark(List<BenchmarkDTO> benchmarkDTOList) {
    	int result = 0;
        String dataJson = gson.toJson(benchmarkDTOList);
        
        //Set entity
        BenchmarkEntity benchmarkEntity = new BenchmarkEntity();
        benchmarkEntity.setInputData(dataJson);
        benchmarkEntity.setCreatedBy(commonHelper.getLoginUser().getUserId());
        benchmarkEntity.setUpdatedBy(commonHelper.getLoginUser().getUserId());
        String data = benchmarkMapper.findAllBenchmark();
        if (StringUtils.isBlank(data)) {
            // Insert to DB
        	result = benchmarkMapper.insertBenchmark(benchmarkEntity);
        } else {
        	result = benchmarkMapper.updateBenchmark(benchmarkEntity);
        }
        
        if (result == 0) {
        	throw new RollBackException(MessageConstants.MSG_UPDATE_ERROR);
        }
    }
}
