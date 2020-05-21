package fr.polytech.recognition.controller.infra;

import fr.polytech.recognition.controller.Controller;
import fr.polytech.recognition.context.ContextHolder;
import fr.polytech.recognition.context.impl.SwingContextHolder;
import fr.polytech.recognition.controller.event.Event;
import fr.polytech.recognition.controller.event.EventManager;
import fr.polytech.recognition.dao.context.DaoContext;
import fr.polytech.recognition.dao.context.impl.NoDaoContext;
import fr.polytech.recognition.view.ViewContext;
import lombok.Getter;

import javax.swing.*;
import java.util.logging.Logger;

public class Router {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> Router.getInstance().init(new SwingContextHolder(), new NoDaoContext()));
    }

    private static final Logger LOGGER = Logger.getLogger("Router");

    private Controller currentController;
    private ControllerRegistry registry;
    @Getter
    private EventManager eventManager;
    private ContextHolder contextHolder;

    private static final Router INSTANCE = new Router();

    public static Router getInstance() {
        return INSTANCE;
    }

    public void init(ContextHolder contextHolder, DaoContext daoContext) {
        daoContext.init();
        this.contextHolder = contextHolder;
        registry = new ControllerRegistry(this);
        contextHolder.init(registry);

        contextHolder.getCurrentContext().init(registry);
        eventManager = new EventManager(registry);
        currentController = registry.getController("chooseImage").get();
        getViewContext().switchView(currentController);
    }

    public void changeController(Controller controller) {
        LOGGER.info("Controller changed to " + controller.getClass().getSimpleName());
        currentController.disposeView();
        currentController = controller;
        getViewContext().switchView(currentController);
    }

    public void dispatchEvent(Event event) {
        eventManager.dispatch(event);
    }

    public <V> V createView(Class<V> view) {
        return contextHolder.getViewFactory().getView(view);
    }

    public ViewContext getViewContext() {
        return contextHolder.getCurrentContext();
    }

    public void nextController(String name) {
        registry.getController(name).ifPresent(this::changeController);
    }

    public void nextController(Controller<?, ?> controller) {
        nextController(ControllerRegistry.getControllerName(controller));
    }
}
