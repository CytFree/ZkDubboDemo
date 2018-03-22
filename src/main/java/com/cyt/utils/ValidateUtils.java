package com.cyt.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author CaoYangTao
 * @date 2018/3/22  20:04
 */
public class ValidateUtils {
    public static <T> void validate (T t) throws Exception {
        Field[] fields = t.getClass().getDeclaredFields();

        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    Object value = getFieldValue(t, field.getName());
                    AnnotationValidateFactory.annotationValidate(value, annotation);
                }
            }
        }
    }

    private static <T> Object getFieldValue(T t, String fieldName) throws Exception {
        Method[]  methods = t.getClass().getMethods();

        for (int i = 0; i < methods.length; i++) {
            if(("get" + fieldName).toLowerCase().equals(methods[i].getName().toLowerCase())){
                Object obj = methods[i].invoke(t);
                return obj;
            }
        }

        return null;
    }
}
