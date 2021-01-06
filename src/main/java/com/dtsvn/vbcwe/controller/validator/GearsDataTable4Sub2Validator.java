package com.dtsvn.vbcwe.controller.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtsvn.vbcwe.dto.GearsDataTable4DTO;
import com.dtsvn.vbcwe.form.GearsDataTable4Sub2Form;
import com.dtsvn.vbcwe.service.GearsDataTable4Service;

@Component
public class GearsDataTable4Sub2Validator implements Validator {

    @Autowired
    GearsDataTable4Service service;

    @Override
    public boolean supports(Class<?> clazz) {
        return GearsDataTable4Sub2Form.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        GearsDataTable4Sub2Form table4Sub2Form = GearsDataTable4Sub2Form.class.cast(target);
        // Lấy dữ liệu trên màn hình
        List<GearsDataTable4DTO> dataTable4DtoList = table4Sub2Form.getDataTable4DtoList();
        // thực hiện validate
        service.validationInCareers(errors, dataTable4DtoList);
    }
}
