package fr.polytech.recognition.utils;

import java.lang.reflect.ParameterizedType;

public class ReflectionUtils {

    public static <T> Class<T> getParameterClass(Class c) {
        return (Class<T>)
                ((ParameterizedType)
                    c.getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
