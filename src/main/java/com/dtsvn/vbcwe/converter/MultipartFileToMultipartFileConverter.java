package com.dtsvn.vbcwe.converter;

import org.dozer.CustomConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileToMultipartFileConverter implements CustomConverter {
    // logger
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        if (sourceFieldValue == null && existingDestinationFieldValue == null) {
            return null;
        }
        try {
            MultipartFile file = (MultipartFile) sourceFieldValue;
            return file;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
