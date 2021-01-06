package com.dtsvn.vbcwe.controller.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.dto.GearsDataTable3DTO;
import com.dtsvn.vbcwe.form.GearsDataTable3Form;

@Component
public class GearsDataTable3Validator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GearsDataTable3Form.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        GearsDataTable3Form table3Form = GearsDataTable3Form.class.cast(target);
        List<GearsDataTable3DTO> dataTable3DtoList = table3Form.getDataTable3DtoList();

        for (GearsDataTable3DTO data : dataTable3DtoList) {
            // Check required
            if (StringUtils.isBlank(data.getTotal())) {
                            errors.rejectValue("dataTable3DtoList", MessageConstants.VALIDATE_MSG_REQUIRED,
                                    new Object[] { "" }, "");
                return;
            }

            // Check maxlength
            if (data.getTotal().length() > 7) {
                errors.rejectValue("dataTable3DtoList", MessageConstants.VALIDATE_MSG_MAX_LENGTH, new Object[] { "7" },
                        "");
                return;
            }

            // Check number
            if (!data.getTotal().matches(Constant.REGEX_ONLY_NUMBER)) {
                errors.rejectValue("dataTable3DtoList", MessageConstants.VALIDATE_MSG_NUMBER, new Object[] { "0" }, "");
                return;
            }
        }
    }
}
