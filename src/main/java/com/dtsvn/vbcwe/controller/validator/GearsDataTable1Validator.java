package com.dtsvn.vbcwe.controller.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.dto.GearsDataTable1DTO;
import com.dtsvn.vbcwe.form.GearsDataTable1Form;

@Component
public class GearsDataTable1Validator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GearsDataTable1Form.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        GearsDataTable1Form table1Form = GearsDataTable1Form.class.cast(target);
        List<GearsDataTable1DTO> dataTable1DtoList = table1Form.getDataTable1DtoList();

        for (GearsDataTable1DTO data : dataTable1DtoList) {
            // Check required
            if (StringUtils.isEmpty(data.getTotalBegin12m()) || StringUtils.isEmpty(data.getRecruitedOutside())
                    || StringUtils.isEmpty(data.getPromotedToLvl()) || StringUtils.isEmpty(data.getLeftCompany())) {
                            errors.rejectValue("dataTable1DtoList", MessageConstants.VALIDATE_MSG_REQUIRED,
                                    new Object[] { "" }, "");
                return;
            }

            // Check maxlength
            if (data.getTotalBegin12m().length() > 7 || data.getRecruitedOutside().length() > 7
                    || data.getPromotedToLvl().length() > 7 || data.getLeftCompany().length() > 7) {
                            errors.rejectValue("dataTable1DtoList", MessageConstants.VALIDATE_MSG_MAX_LENGTH,
                                    new Object[] { "7" }, "");
                return;
            }

            // Check number
            if (!data.getTotalBegin12m().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getRecruitedOutside().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getPromotedToLvl().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getLeftCompany().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getTotalEnd12m().matches(Constant.REGEX_ONLY_NUMBER)) {
                errors.rejectValue("dataTable1DtoList", MessageConstants.VALIDATE_MSG_NUMBER, new Object[] { "0" }, "");
                return;
            }
        }
    }
}
