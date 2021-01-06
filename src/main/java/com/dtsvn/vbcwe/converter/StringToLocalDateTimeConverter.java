package com.dtsvn.vbcwe.converter;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.util.CommonUtils;
import org.dozer.CustomConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter implements CustomConverter {
    // logger
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        if (sourceFieldValue == null && existingDestinationFieldValue == null) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_DDMMYYYY);
            if (sourceClass.getName().equals(LocalDateTime.class.getName())) {
                if (destinationClass.getName().equals(String.class.getName())) {
                    return ((LocalDateTime) sourceFieldValue).format(formatter);
                }
            } else if (sourceClass.getName().equals(String.class.getName())) {
                if (CommonUtils.isDateFormat(sourceFieldValue.toString(), Constant.DATE_TIME_PATTERN)) {
                    return CommonUtils.stringToLocalDateTime(sourceFieldValue.toString(), Constant.DATE_TIME_PATTERN);
                } else if (CommonUtils.isDateFormat(sourceFieldValue.toString(), Constant.DATE_FORMAT_DDMMYYYY)) {
                    if (destinationClass.getName().equals(LocalDate.class.getName())) {
                        return LocalDate.parse(sourceFieldValue.toString(), formatter);
                    }
                    if (destinationClass.getName().equals(LocalDateTime.class.getName())) {
                        return LocalDate.parse((String) sourceFieldValue, formatter).atStartOfDay();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
