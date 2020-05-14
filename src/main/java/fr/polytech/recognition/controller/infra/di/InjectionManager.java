package fr.polytech.recognition.controller.infra.di;

import java.util.HashMap;

public class InjectionManager {

    private static final HashMap<String, Object> INJECTABLE = new HashMap<>();

    private InjectionManager() {}

    public static void register(String name, Object value) {
        INJECTABLE.put(name, value);
    }

    public static Object get(Class<?> key) {
        return INJECTABLE.get(key.getSimpleName());
    }
}
