package fr.polytech.recognition.controller.routingevent;

import fr.polytech.recognition.controller.infra.Controller;
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
        Set<Method> eventHandler = new Reflections("fr.polytech.recognition.controller", new MethodAnnotationsScanner()).getMethodsAnnotatedWith(EventHandler.class);
        for (Method method : eventHandler) {
            if (method.getParameterCount() == 1) {
                //TODO verifier que le param extend Event
                String eventId = method.getParameters()[0].getType().getSimpleName();
                String contrId = method.getDeclaringClass().getAnnotation(ControllerRegistration.class).name();
                Optional<Controller> controller = registry.getController(contrId);
                List<EventRegistration> registrations = EVENT_REGISTRATION.computeIfAbsent(eventId, k -> new ArrayList<>());
                registrations.add(new EventRegistration(controller.get(), method));
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
