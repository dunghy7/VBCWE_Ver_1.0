package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.BenchmarkDTO;

import lombok.Data;

@Data
public class BenchmarkForm {

	private List<BenchmarkDTO> benchmarkList;
}
