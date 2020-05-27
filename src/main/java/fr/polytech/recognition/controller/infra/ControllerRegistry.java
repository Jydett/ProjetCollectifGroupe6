package fr.polytech.recognition.controller.infra;

import fr.polytech.recognition.controller.Controller;
import fr.polytech.recognition.controller.infra.di.Inject;
import fr.polytech.recognition.controller.infra.di.InjectionManager;
import fr.polytech.recognition.exception.RegistrationError;
import fr.polytech.recognition.model.Model;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public final class ControllerRegistry {

    private static final HashMap<String, LazyController> CONTROLLERS = new HashMap<>();

    public ControllerRegistry(Router router) {
        registerController(router);
    }

    private void registerController(Router router) {
        InjectionManager.scan("fr.polytech.recognition.model");
        Set<Class<?>> registeredControllers =
                new Reflections("fr.polytech.recognition.controller")
                .getTypesAnnotatedWith(ControllerRegistration.class);
        LazyController.router = router;
        registeredControllers.forEach(controllerClass -> {
            Constructor<?>[] constructors = controllerClass.getConstructors();
            if (constructors.length < 1) {
                throw new RegistrationError("No constructor in an @ControllerRegistration annotated class (" + controllerClass.getSimpleName() + ")");
            }
            Constructor<?> constructor = constructors[0];
            if (constructor.getParameterCount() != 2) {
                throw new RegistrationError("Constructor has incorrect count of parameters (" + controllerClass.getSimpleName() + ") => should have 2 parameters");
            }
            Class<?> firstParameter = constructor.getParameterTypes()[0];
            Class<?> secondParameter = constructor.getParameterTypes()[1];
            boolean classicOrder = Router.class.isAssignableFrom(firstParameter) && Model.class.isAssignableFrom(secondParameter);
            if (!(classicOrder
                    ||
                (Model.class.isAssignableFrom(firstParameter) && Router.class.isAssignableFrom(secondParameter)))) {
                throw new RegistrationError("Constructor must have the Router and a Model as parameters (" + controllerClass.getSimpleName() + ")");
            }
            //FIXME bruh ce serais pas mieux d'obliger que le router et d'injecter le model
            // parceque la c'est très le moment fréro de faire tout ca juste pour injecter
            // le model alors qu'on passe par l'injectionManager de toute facon
            // @Arthur
            CONTROLLERS.put(controllerClass.getAnnotation(ControllerRegistration.class).name(),
                    new LazyController(classicOrder, (Model) InjectionManager.get(classicOrder ? secondParameter : firstParameter), constructor));
        });
    }

    public <T extends Controller> Optional<T> getController(String name) {
        LazyController lazyController = CONTROLLERS.get(name);
        if (lazyController == null) {
            return Optional.empty();
        }
        return Optional.of((T) lazyController.get());
    }

    public static String getControllerName(Controller<?, ?> controller) {//TODO error case
        return controller.getClass().getAnnotation(ControllerRegistration.class).name();
    }

    public Iterator<? extends Supplier<Controller<?, ?>>> iterator() {
        return CONTROLLERS.values().iterator();
    }

    @RequiredArgsConstructor
    private static class LazyController implements Supplier<Controller<?, ?>> {

        private static Router router;
        private final boolean constructorOrder;
        private final Model model;
        private final Constructor<?> constructor;
        private Controller instance;

        public Controller get() {
            if (instance == null) {
                try {
                    instance = (Controller) (constructorOrder ? constructor.newInstance(router, model) : constructor.newInstance(router, model));
                } catch (Exception e) {
                    throw new RegistrationError("An error has occured while creating the controller " + constructor.getDeclaringClass().getSimpleName(), e);
                }
                InjectionManager.injectDependencies(instance);
                instance.init();
            }
            return instance;
        }
    }
}
