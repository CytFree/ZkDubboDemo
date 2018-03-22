package com.cyt.annotation;

import java.lang.annotation.*;

/**
 * 长度注解
 *
 * @author CaoYangTao
 * @date 2018/3/22  20:47
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface LengthValidate {
    String value() default "";
    /**
     * 最小长度
     *
     * @return
     */
    int minLength() default 0;

    /**
     * 最大长度
     *
     * @return
     */
    int maxLength() default 0;
}
