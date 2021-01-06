package com.dtsvn.vbcwe.controller.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.dto.BenchmarkDTO;
import com.dtsvn.vbcwe.form.BenchmarkForm;

@Component
public class BenchmarkValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BenchmarkForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        BenchmarkForm benchmarkForm = BenchmarkForm.class.cast(target);
        List<BenchmarkDTO> benchmarkDtoList = benchmarkForm.getBenchmarkList();

        for (BenchmarkDTO data : benchmarkDtoList) {
            // Check required
            if (StringUtils.isBlank(data.getLevel()) || StringUtils.isBlank(data.getAverageWomen())
                    || StringUtils.isBlank(data.getMaxDiff()) || StringUtils.isBlank(data.getMinDiff())) {
                errors.rejectValue("benchmarkList", MessageConstants.VALIDATE_MSG_REQUIRED, new Object[] { "" }, "");
                return;
            }

            // Check maxlength
            if (data.getLevel().length() > 7 || data.getAverageWomen().length() > 7 || data.getMaxDiff().length() > 7
                    || data.getMinDiff().length() > 7) {
                errors.rejectValue("benchmarkList", MessageConstants.VALIDATE_MSG_MAX_LENGTH, new Object[] { "7" }, "");
                return;
            }

            // Check number
            if (!data.getLevel().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getAverageWomen().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getMaxDiff().matches(Constant.REGEX_ONLY_NUMBER)
                    || !data.getMinDiff().matches(Constant.REGEX_ONLY_NUMBER)) {
                errors.rejectValue("benchmarkList", MessageConstants.VALIDATE_MSG_NUMBER, new Object[] { "0" }, "");
                return;
            }
        }
    }

}
