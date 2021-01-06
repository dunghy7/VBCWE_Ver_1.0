package com.dtsvn.vbcwe.dto;

import java.util.List;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberSurveyDTO {
	
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 200, message = "Không được quá {0} ký tự")
	private String companyName;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 500, message = "Không được quá {0} ký tự")
	private String address;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 50, message = "Không được quá {0} ký tự")
	private String district;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 50, message = "Không được quá {0} ký tự")
	private String city;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 100, message = "Không được quá {0} ký tự")
	private String fullName;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 200, message = "Không được quá {0} ký tự")
	private String position;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 15, message = "Không được quá {0} ký tự")
	private String phone;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 15, message = "Không được quá {0} ký tự")
	private String mobile;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 15, message = "Không được quá {0} ký tự")
	private String fax;
	@NotEmpty(message = "Thông tin không được để trống")
	@Max(value = 50, message = "Không được quá {0} ký tự")
	private String email;

	private List<SurveyDTO> survey;


}
