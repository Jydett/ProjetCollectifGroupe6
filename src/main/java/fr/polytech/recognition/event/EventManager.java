package fr.polytech.recognition.event;

import fr.polytech.recognition.controller.Controller;
import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.ControllerRegistry;
import lombok.AllArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

public class EventManager {
    private static final Logger LOGGER = Logger.getLogger("EventManager");

    private static final HashMap<String, List<EventRegistration>> EVENT_REGISTRATION = new HashMap<>();

    public EventManager(ControllerRegistry registry) {
        Set<Method> eventHandler = new Reflections("fr.polytech.recognition.controller", new MethodAnnotationsScanner())
                .getMethodsAnnotatedWith(EventHandler.class);
        for (Method method : eventHandler) {
            Class<?> declaringClass = method.getDeclaringClass();
            ControllerRegistration annotation = declaringClass.getAnnotation(ControllerRegistration.class);
            String contrId = annotation.name();
            Optional<Controller> controller = registry.getController(contrId);
            String eventId = method.getParameters()[0].getType().getSimpleName();
            EventRegistration registration = new EventRegistration(controller.get(), method);
            List<EventRegistration> registrations = EVENT_REGISTRATION.computeIfAbsent(eventId, k -> new ArrayList<>());
            registrations.add(registration);
        }
    }

    public void register(Object o) {
        Class<?> declaringClass = o.getClass();
        for (Method method : declaringClass.getDeclaredMethods()) {
            if (method.getAnnotation(EventHandler.class) != null) {
                EventRegistration registration = new EventRegistration(o, method);
                String eventId = method.getParameters()[0].getType().getSimpleName();
                List<EventRegistration> registrations = EVENT_REGISTRATION.computeIfAbsent(eventId, k -> new ArrayList<>());
                registrations.add(registration);
            }
        }
    }

    public void dispatch(Event event) {
        String eventName = event.getClass().getSimpleName();
        List<EventRegistration> registrations = EVENT_REGISTRATION.get(eventName);
        if (registrations == null) return;
        call(event, registrations);
    }

    private void call(Event event, List<EventRegistration> registrations) {
        LOGGER.info("Event " + event.getClass().getSimpleName() + " dispatched to " + registrations.size() + " listener(s)");
        for (EventRegistration registration : registrations) {
            try {
                registration.toCall.invoke(registration.parent, event);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    @AllArgsConstructor
    public class EventRegistration {
        private final Object parent;
        private final Method toCall;
    }
}
