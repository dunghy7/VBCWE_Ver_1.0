package com.dtsvn.vbcwe.common.anotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface Column {

    String name() default "N/A";

    boolean alwayUpdate() default  false;
}
