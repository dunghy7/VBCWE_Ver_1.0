package com.dtsvn.vbcwe.form;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberForm {
	
    @NotBlank(message = "Tên công ty không được để trống.")
	@Max(value = 200, message = "Không được vượt quá {0} kí tự.")
	private String companyName;
	
    @NotBlank(message = "Địa chỉ không được để trống.")
	@Max(value = 500, message = "Không được vượt quá {0} kí tự.")
	private String address;
	
    @NotBlank(message = "Quận không được để trống.")
	@Max(value = 50, message = "Không được vượt quá {0} kí tự.")
	private String district;
	
    @NotBlank(message = "Thành phố không được để trống.")
	@Max(value = 50, message = "Không được vượt quá {0} kí tự.")
	private String city;
	
    @NotBlank(message = "Họ tên không được để trống.")
	@Max(value = 100, message = "Không được vượt quá {0} kí tự.")
	private String fullName;
	
    @NotBlank(message = "Chức vụ không được để trống.")
	@Max(value = 200, message = "Không được vượt quá {0} kí tự.")
	private String position;
	
    @NotBlank(message = "Số điện thoại bàn không được để trống.")
	@Max(value = 15, message = "Không được vượt quá {0} kí tự.")
    @Pattern(regexp = "[0-9]+", message = "Chỉ được điền số.")
	private String phone;
	
    @NotBlank(message = "Số di động không được để trống.")
	@Max(value = 15, message = "Không được vượt quá {0} kí tự.")
    @Pattern(regexp = "[0-9]+", message = "Chỉ được điền số.")
	private String mobile;
	
    @NotBlank(message = "Số fax không được để trống.")
	@Max(value = 15, message = "Không được vượt quá {0} kí tự.")
    @Pattern(regexp = "[0-9]+", message = "Chỉ được điền số.")
	private String fax;
	
    @NotBlank(message = "Email không được để trống.")
	@Max(value = 50, message = "Không được vượt quá {0} kí tự.")
	@Email(message = "Email không hợp lệ.")
	private String email;

	private List<SurveyForm> survey;


}
