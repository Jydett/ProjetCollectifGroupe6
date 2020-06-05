package fr.polytech.recognition.controller.infra.di;

import fr.polytech.recognition.exception.RegistrationError;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

public class InjectionManager {

    private static final HashMap<String, Object> INJECTABLE = new HashMap<>();

    private InjectionManager() {}

    public static void register(Class<?> registeredClass, Object value) {
        INJECTABLE.put(registeredClass.getSimpleName(), value);
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

    public static void injectDependencies(Object object) {
        try {
            for (Field field : object.getClass().getFields()) {
                if (field.getAnnotation(Inject.class) != null) {
                    field.setAccessible(true);
                    field.set(object, InjectionManager.get(field.getType()));
                }
            }
        } catch (Exception e) {
            throw new RegistrationError("An error has occurred while injecting values in constructor " + object.getClass().getSimpleName(), e);
        }
    }
}
