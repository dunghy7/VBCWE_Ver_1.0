package com.dtsvn.vbcwe.converter;

import java.time.LocalDateTime;

import org.dozer.CustomConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalDateTimeToLocalDateTimeConverter implements CustomConverter  {
	// logger
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		if(sourceFieldValue == null) {
			return null;
		}
		logger.warn("LocalDateTimeToLocalDateTimeConverter");
		try {
			LocalDateTime currentDateTime = (LocalDateTime) sourceFieldValue;
            return currentDateTime;
			
        } catch (Exception e) {
        	logger.warn("LocalDateTimeToLocalDateTimeConverter:" + e.toString());
            return null;
        }
	}

}
