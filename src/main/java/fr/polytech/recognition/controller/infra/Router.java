package fr.polytech.recognition.controller.infra;

import fr.polytech.recognition.ai.impl.TensorflowClassifier;
import fr.polytech.recognition.context.ClassifierContext;
import fr.polytech.recognition.context.impl.ai.LabelToArticleTypeTransformationMethod;
import fr.polytech.recognition.controller.Controller;
import fr.polytech.recognition.context.ViewContextHolder;
import fr.polytech.recognition.context.impl.view.SwingViewContextHolder;
import fr.polytech.recognition.controller.infra.di.InjectionManager;
import fr.polytech.recognition.event.Event;
import fr.polytech.recognition.event.EventManager;
import fr.polytech.recognition.dao.context.DaoContext;
import fr.polytech.recognition.dao.context.impl.NoDaoContext;
import fr.polytech.recognition.view.ViewContext;
import lombok.Getter;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Classe principale de l'application
 *
 * Sert de Facade pour
 * <ul>
 *     <li>le {@see ControllerRegistry}</li>
 *     <li>l' {@see EventManager}</li>
 *     <li>le {@see ContextHolder}</li>
 *     <li>le {@see ClassifierContext}</li>
 *     <li>le {@see DaoContext}</li>
 * </ul>
 *
 * C'est un singleton ({@see #getInstance})
 */
public class Router {

    /**
     * Point d'entrée de l'application, par défaut l'application est paramétrée
     * pour utilisé une vue tabbulaire sous Swing, des daos sous hibernate
     * une base de reconnaissance avec Tensorflow
     * @param args non-utilisé
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> Router.getInstance().init(
            new SwingViewContextHolder(), new NoDaoContext(),
            new ClassifierContext<>(
                new TensorflowClassifier(
                "/tensorflow_inception_graph.pb",
                "/imagenet_comp_graph_label_strings.txt",
                224,224, 117f, 1f),
                new LabelToArticleTypeTransformationMethod()
            ))
        );
    }

    private static final Logger LOGGER = Logger.getLogger("Router");

    private Controller currentController;
    private ControllerRegistry registry;
    @Getter
    private EventManager eventManager;
    private ViewContextHolder viewContextHolder;
    private DaoContext daoContext;
    private ClassifierContext<?, ?> classifierContext;

    private static final Router INSTANCE = new Router();

    public static Router getInstance() {
        return INSTANCE;
    }

    public void init(ViewContextHolder viewContextHolder, DaoContext daoContext, ClassifierContext<?, ?> classifierContext) {
        this.daoContext = daoContext;
        this.classifierContext = classifierContext;
        daoContext.init();
        this.viewContextHolder = viewContextHolder;
        registry = new ControllerRegistry(this);
        viewContextHolder.init(registry);
        viewContextHolder.getCurrentContext().init(registry);
        eventManager = new EventManager(registry);
        eventManager.register(classifierContext);
        InjectionManager.injectDependencies(classifierContext.getTransformationMethod());
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
        return viewContextHolder.getViewFactory().getView(view);
    }

    public ViewContext getViewContext() {
        return viewContextHolder.getCurrentContext();
    }

    public void nextController(String name) {
        registry.getController(name).ifPresent(this::changeController);
    }

    public void nextController(Controller<?, ?> controller) {
        nextController(ControllerRegistry.getControllerName(controller));
    }
}
