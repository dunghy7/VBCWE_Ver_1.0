package com.dtsvn.vbcwe.common.anotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface UniqueKey {

	String name() default "N/A";

	String message() default "";
}
