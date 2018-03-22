package com.cyt.utils;

import com.cyt.annotation.NotNull;
import com.cyt.exception.ValidateException;

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
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof NotNull) {
                        Object value = getFieldValue(t, field.getName());
                        boolean isBank = value == null || (value instanceof String && "".equals(value));
                        if (isBank) {
                            throw new ValidateException(((NotNull) annotation).value() + "，" + ((NotNull) annotation).msg());
                        }
                    }
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
