package io.github.lobt.refitretrofit.service.http.converter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Lobt
 * @version 0.1
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface QueryKey {

    String value() default "";
}
