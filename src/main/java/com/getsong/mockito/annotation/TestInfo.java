package com.getsong.mockito.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 7/10/2019 7:49 PM
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestInfo {
  String seriousLevel() default "very serious";
}
