package fr.polytech.recognition.controller.infra;

import fr.polytech.recognition.controller.infra.context.ContextHolder;
import fr.polytech.recognition.controller.infra.impl.SwingContextHolder;
import fr.polytech.recognition.controller.routingevent.Event;
import fr.polytech.recognition.controller.routingevent.EventManager;
import fr.polytech.recognition.dao.context.DaoContext;
import fr.polytech.recognition.dao.context.impl.NoDaoContext;

import javax.swing.*;
import java.util.logging.Logger;

public class Router {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Router(new SwingContextHolder(), new NoDaoContext()));
    }

    private static final Logger LOGGER = Logger.getLogger("Router");

    private Controller currentController;
    private final ControllerRegistry registry;
    private final EventManager eventManager;
    private final ContextHolder contextHolder;

    public Router(ContextHolder contextHolder, DaoContext daoContext) {
        daoContext.init();
        this.contextHolder = contextHolder;
        registry = new ControllerRegistry(this);
        eventManager = new EventManager(registry);
        contextHolder.init(registry);

        registry.initControllers();
        contextHolder.getCurrentContext().init();
        currentController = registry.getController("chooseImage").get();
        currentController.showView();
    }

    public void changeController(Controller controller) {
        LOGGER.info("Controller changed to " + controller.getClass().getSimpleName());
        currentController.disposeView();
        currentController = controller;
        currentController.showView();
    }

    public void nextController(String name) {
        registry.getController(name).ifPresent(this::changeController);
    }

    public void dispatchEvent(Event event) {
        eventManager.dispatch(event);
    }

    public <V> V createView(Class<V> view) {
        return contextHolder.getViewFactory().getView(view);
    }
}
