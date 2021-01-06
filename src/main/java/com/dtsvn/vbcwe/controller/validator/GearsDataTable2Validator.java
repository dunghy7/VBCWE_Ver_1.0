package com.dtsvn.vbcwe.controller.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.dto.GearsDataTable2DTO;
import com.dtsvn.vbcwe.form.GearsDataTable2Form;

@Component
public class GearsDataTable2Validator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GearsDataTable2Form.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        GearsDataTable2Form table2Form = GearsDataTable2Form.class.cast(target);
        List<GearsDataTable2DTO> dataTable2DtoList = table2Form.getDataTable2DtoList();

        for (GearsDataTable2DTO data : dataTable2DtoList) {
            // Check required
            if (StringUtils.isEmpty(data.getIndefinitelyContract()) || StringUtils.isEmpty(data.getLimitedContract())
                    || StringUtils.isEmpty(data.getPartTime())) {
                errors.rejectValue("dataTable2DtoList", MessageConstants.VALIDATE_MSG_REQUIRED, new Object[] { "" },
                        "");
                return;
            }

            // Check maxlength
            if (data.getIndefinitelyContract().length() > 7 || data.getLimitedContract().length() > 7
                    || data.getPartTime().length() > 7) {
                errors.rejectValue("dataTable2DtoList", MessageConstants.VALIDATE_MSG_MAX_LENGTH, new Object[] { "7" },
                        "");
                return;
            }

            // Check number
            if (!data.getIndefinitelyContract().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getLimitedContract().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getPartTime().matches(Constant.REGEX_ONLY_NUMBER)) {
                errors.rejectValue("dataTable2DtoList", MessageConstants.VALIDATE_MSG_NUMBER, new Object[] { "0" }, "");
                return;
            }

            // check giá trị cột [Tổng số nhân viên tại cuối kỳ tham chiếu] và [] cột [Kiểm
            // tra dữ liệu]
            if ((Integer.parseInt(data.getTotal()) == Integer.parseInt(data.getCheckAgain())) == false) {
                errors.rejectValue("dataTable2DtoList", "vbcwe.gears.compareColumn",
                        new Object[] { "Tổng số nhân viên tại cuối kỳ tham chiếu", "Kiểm tra dữ liệu" }, "");
                return;
            }
        }
    }
}
