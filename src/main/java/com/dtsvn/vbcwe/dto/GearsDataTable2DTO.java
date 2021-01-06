package com.dtsvn.vbcwe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GearsDataTable2DTO {

	/**
	 * Cấp bậc
	 */
	private String level;
	
	/**
	 * Giới tính
	 */
	private String gender;
	
	/**
	 * Nhân viên toàn thời gian, có hợp đồng không xác định thời hạn tại cuối kỳ tham chiếu
	 */
	private String indefinitelyContract;
	
	/**
	 * Nhân viên toàn thời gian, hợp đồng có xác định thời gian tại cuối kỳ tham chiếu
	 */
	private String limitedContract;
	
	/**
	 * Nhân viên làm việc bán thời gian tại cuối kỳ tham chiếu
	 */
	private String partTime;
	
	/**
	 * Tổng số nhân viên tại cuối kỳ tham chiếu
	 */
	private String total;
	
	/**
	 * Kiểm tra dữ liệu
	 */
	private String checkAgain;
}
