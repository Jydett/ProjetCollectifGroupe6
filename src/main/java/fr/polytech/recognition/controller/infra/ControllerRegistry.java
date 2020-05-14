package fr.polytech.recognition.controller.infra;

import fr.polytech.recognition.controller.infra.di.Inject;
import fr.polytech.recognition.controller.infra.di.InjectionManager;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public final class ControllerRegistry {

    private static final HashMap<String, Controller> CONTROLLERS = new HashMap<>();

    public ControllerRegistry(Router router) {
        registerController(router);
    }

    private void registerController(Router router) {
        Set<Class<?>> registeredControllers =
                new Reflections("fr.polytech.recognition.controller")
                .getTypesAnnotatedWith(ControllerRegistration.class);
        registeredControllers.forEach(controllerClass -> {
            try {
                Object controller = controllerClass.getConstructors()[0].newInstance(router);
                for (Field field : controller.getClass().getFields()) {
                    if (field.getAnnotation(Inject.class) != null) {
                        field.setAccessible(true);
                        field.set(controller, InjectionManager.get(field.getType()));
                    }
                }
                CONTROLLERS.put(controllerClass.getAnnotation(ControllerRegistration.class).name(),
                        (Controller) controller);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public <T extends Controller> Optional<T> getController(String name) {
        return Optional.ofNullable((T) CONTROLLERS.get(name));
    }

    public void initControllers() {
        CONTROLLERS.values().forEach(Controller::init);
    }
}
