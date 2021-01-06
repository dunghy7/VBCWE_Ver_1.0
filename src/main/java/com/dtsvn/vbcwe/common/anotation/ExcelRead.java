package com.dtsvn.vbcwe.common.anotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface ExcelRead {
	int startRow() default 0;

	String sheet() default "";

	String column() default "";
}
