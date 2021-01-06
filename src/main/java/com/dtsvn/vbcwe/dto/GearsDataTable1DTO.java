package com.dtsvn.vbcwe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GearsDataTable1DTO {

	/**
	 * Cấp bậc
	 */
	private String level;
	
	/**
	 * Giới tính
	 */
	private String gender;
	
	/**
	 * Tổng số nhân viên đầu kỳ tham chiếu 12 tháng
	 */
	private String totalBegin12m;
	
	/**
	 * Tuyển dụng vào cấp độ này từ bên ngoài công ty
	 */
	private String recruitedOutside;
	
	/**
	 * Thăng chức lên cấp độ này trong nội bộ công ty
	 */
	private String promotedToLvl;
	
	/**
	 * Thăng chức từ cấp độ này lên cấp cao hơn
	 */
	private String promotedFromLvl;
	
	/**
	 * Tổng sô nhân viên nghỉ việc từ cấp độ này
	 */
	private String leftCompany;
	
	/**
	 * Tổng số nhân viên cuối kỳ tham chiếu 12 tháng
	 */
	private String totalEnd12m;
}
