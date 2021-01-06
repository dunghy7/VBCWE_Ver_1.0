package com.dtsvn.vbcwe.controller.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class FormSurveyValidator<T> {
	public HashMap<String, String> validator(T value) throws IllegalArgumentException, IllegalAccessException {
		return getAllFields(value.getClass(), value, null);
	}

	public HashMap<String, String>  getAllFields(Class klass, Object value, Integer index) throws IllegalArgumentException, IllegalAccessException {
		HashMap<String, String> validators = new HashMap<String, String>();

		List<Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(klass.getDeclaredFields()));
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			field.setAccessible(true);
			if (Collection.class.isAssignableFrom(field.getType())) {
				Type genericFieldType = field.getGenericType();

				if (genericFieldType instanceof ParameterizedType) {
					ParameterizedType aType = (ParameterizedType) genericFieldType;
					if (isJavaObject((Class) aType.getActualTypeArguments()[0])) {
						List<Object> test = (List<Object>) field.get(value);
						for (int j = 0; j < test.size(); j++) {
							validators.putAll(getAllFields((Class) aType.getActualTypeArguments()[0], test.get(j) , j));
						}

					}
				}
			} else if (isJavaObject(field.getType())) {
				validators.putAll(getAllFields(field.getType(), field.get(value), null));
			} else {
				Annotation[] annotations = field.getDeclaredAnnotations();
				for (int j = 0; j < annotations.length; j++) {
					Annotation annotation = annotations[j];
					String fieldName = field.getName() + (index != null ? "_" + index.toString() : "");

					Object fieldValue = field.get(value);
					if (Objects.equals(annotation.annotationType(), NotBlank.class)) {
					    NotBlank notBlank = (NotBlank) annotation;
						if(fieldValue == null || StringUtils.isBlank(fieldValue.toString())) {
							validators.put(fieldName, notBlank.message());
							break;
						}
						
					} else if (Objects.equals(annotation.annotationType(), Max.class)) {
						Max m = (Max) annotation;
                        if (fieldValue != null && !StringUtils.isBlank(fieldValue.toString())
                                && fieldValue.toString().length() > m.value()) {
                            Object[] obj = new Object[] { m.value() };
                            validators.put(fieldName, new MessageFormat(m.message()).format(obj));
                            break;
                        }
					} else if (Objects.equals(annotation.annotationType(), Email.class)) {
					    Email email = (Email) annotation;
					    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
					    if (!fieldValue.toString().matches(regex)) {
					        validators.put(fieldName, email.message());
                            break;
					    }
					} else if (Objects.equals(annotation.annotationType(), Pattern.class)) {
					    Pattern pattern = (Pattern) annotation;
                        String regex = pattern.regexp();
                        if (!fieldValue.toString().matches(regex)) {
                            validators.put(fieldName, pattern.message());
                            break;
                        }
                    }
				}
			}

		}
		return validators;
	}

	public boolean isJavaObject(Class clazz) {
		return (clazz.getName().startsWith("com.dtsvn.vbcwe"));
	}
}
