package com.dtsvn.vbcwe.form;

import javax.validation.constraints.AssertTrue;

import org.apache.poi.util.StringUtil;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.dto.AllInputInfoDTO;
import com.dtsvn.vbcwe.dto.ReportDTO;

import lombok.Data;

@Data
public class AllInputInfoForm {
	/**
	 * Bảng câu hỏi đánh giá
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean smStatus;
	
	/**
	 * Bảng 1: Dịch chuyển lao động
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean ienStatus;
	
	/**
	 * Bảng 2: Việc làm
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean ieStatus;
	
	/**
	 * Bảng 3: Ban lãnh đạo
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean ibcStatus;
	
	/**
	 * Bảng 4.1: Quản lý
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean icmStatus;
	
	/**
	 * Bảng 4.2: Chuyên gia
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean iceStatus;
	
	/**
	 * Bảng 4.3: Kỹ thuật viên và trợ lý chuyên gia
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean ictStatus;
	
	/**
	 * Bảng 4.4: Nhân viên trợ lý văn phòng
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean icoStatus;
	
	/**
	 * Bảng 4.5: Nhân viên dịch vụ và bán hàng
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean icsStatus;
	
	/**
	 * Bảng 4.6: Lao động có kỹ năng trong nông nghiệp, lâm nghiệp và thủy sản
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean icaStatus;
	
	/**
	 * Bảng 4.7: Lao động thủ công và các nghề nghiệp có liên quan khác
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean icmlStatus;
	
	/**
	 * Bảng 4.8: Thợ lắp ráp và vận hành máy móc, thiết bị
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean icsaStatus;
	
	/**
	 * Bảng 4.9: Lao động giản đơn
	 */
	@AssertTrue(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NOT_COMPLETED + Constant.RIGHT_BRACES)
	private Boolean icspStatus;

	/**
	 * Kết quả báo cáo
	 */
	private Boolean surveyStatus;
	
	/**
	 * Đường dẫn file 
	 */
	private String surveyPath;

	public AllInputInfoForm() {

	}

	public AllInputInfoForm(AllInputInfoDTO allInputInfoDto, ReportDTO reportDTO) {

		if (allInputInfoDto != null) {
			this.icmStatus = allInputInfoDto.getIcmStatus();

			this.iceStatus = allInputInfoDto.getIceStatus();

			this.ictStatus = allInputInfoDto.getIctStatus();

			this.icoStatus = allInputInfoDto.getIcoStatus();

			this.icsStatus = allInputInfoDto.getIcsStatus();

			this.icaStatus = allInputInfoDto.getIcaStatus();

			this.icmlStatus = allInputInfoDto.getIcmlStatus();

			this.icsaStatus = allInputInfoDto.getIcsaStatus();

			this.icspStatus = allInputInfoDto.getIcspStatus();

			this.smStatus = allInputInfoDto.getSmStatus();

			this.ienStatus = allInputInfoDto.getIenStatus();

			this.ieStatus = allInputInfoDto.getIeStatus();

			this.ibcStatus = allInputInfoDto.getIbcStatus();

			this.surveyStatus = Constant.MEMBER_REPORT_STATUS.REPORT_APPROVED.value.equals(reportDTO.getStatus()) ? true : false;

            this.surveyPath = reportDTO.getFilePath() != null ? reportDTO.getFilePath().replace(".pptx", ".pdf") : null;
		}
	}
}
