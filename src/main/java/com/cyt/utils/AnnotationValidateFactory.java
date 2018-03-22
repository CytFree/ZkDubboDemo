package com.cyt.utils;

import com.cyt.annotation.LengthValidate;
import com.cyt.annotation.NotNull;
import com.cyt.exception.ValidateException;

import java.lang.annotation.Annotation;

/**
 * @author CaoYangTao
 * @date 2018/3/22  20:55
 */
public class AnnotationValidateFactory {
    public static void annotationValidate(Object value, Annotation annotation) {
        if (annotation instanceof NotNull) {
            notNullAnnotationValidate(value, annotation);
        } else if (annotation instanceof LengthValidate) {
            noLengthAnnotationValidate(value, annotation);
        }
    }

    public static void notNullAnnotationValidate(Object value, Annotation annotation) {
        NotNull notNullAnnotation = (NotNull) annotation;
        boolean isBank = value == null || (value instanceof String && "".equals(value));
        if (isBank) {
            throw new ValidateException(notNullAnnotation.value() + "，" + notNullAnnotation.msg());
        }
    }

    public static void noLengthAnnotationValidate(Object value, Annotation annotation) {
        LengthValidate lengthValidateAnnotation = (LengthValidate) annotation;
        int length = value == null ? 0 : value.toString().length();
        boolean notInRange = length < lengthValidateAnnotation.minLength()
                || length > lengthValidateAnnotation.maxLength();
        if (notInRange) {
            throw new ValidateException(lengthValidateAnnotation.value() + "，长度范围["
                    + lengthValidateAnnotation.minLength() + ","
                    + lengthValidateAnnotation.maxLength() + "]");
        }
    }
}
