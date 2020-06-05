package fr.polytech.recognition.utils;

import java.lang.reflect.ParameterizedType;

public class ReflectionUtils {

    /**
     * Return the class of the parameter of a parametrized class
     * @param c the parametrized class
     * @param <T> return class
     * @return the type of the parameter
     */
    public static <T> Class<T> getParameterClass(Class c) {
        return (Class<T>)
                ((ParameterizedType)
                    c.getGenericSuperclass())
                .getActualTypeArguments()[1];
    }
}
