package com.cyt.annotation;

import java.lang.annotation.*;

/**
 * @author CaoYangTao
 * @date 2018/3/22  19:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD, ElementType.FIELD})
public @interface NotNull {
    String value() default "";
    String msg() default "";
}
