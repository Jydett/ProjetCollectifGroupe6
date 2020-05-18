package fr.polytech.recognition.controller.infra.di;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class InjectionManager {

    private static final HashMap<String, Object> INJECTABLE = new HashMap<>();

    private InjectionManager() {}

    public static void register(String name, Object value) {
        INJECTABLE.put(name, value);
    }

    public static Object get(Class<?> key) {
        return INJECTABLE.get(key.getSimpleName());
    }

    public static void scan(String packageName) {
        Set<Class<?>> injectables = new Reflections(packageName).getTypesAnnotatedWith(Injectable.class);
        for (Class<?> injectable : injectables) {
            try {
                INJECTABLE.put(injectable.getSimpleName(), injectable.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
