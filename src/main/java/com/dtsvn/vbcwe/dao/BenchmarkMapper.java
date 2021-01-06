package com.dtsvn.vbcwe.dao;

import org.apache.ibatis.annotations.Mapper;

import com.dtsvn.vbcwe.entity.BenchmarkEntity;

@Mapper
public interface BenchmarkMapper {

    /**
     * find all benchmark
     * @return
     */
    String findAllBenchmark();

    /**
     * insert benchmark
     * @param benchmarkEntity
     * @return
     */
    Integer insertBenchmark(BenchmarkEntity benchmarkEntity);
    
    /**
     * update benchmark
     * @param benchmarkEntity
     * @return
     */
    Integer updateBenchmark(BenchmarkEntity benchmarkEntity);
}
