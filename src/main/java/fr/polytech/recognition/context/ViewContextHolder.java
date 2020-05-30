package fr.polytech.recognition.context;

import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.view.ViewContext;

/**
 * Enveloppe un {@see ViewContext} & un {@see ViewFactory} et les initialisent
 */
public interface ViewContextHolder {

    ViewContext getCurrentContext();

    ViewFactory getViewFactory();

    /**
     * Initialise le ViewContext & la ViewFactory
     * @param registry le registre des controlleurs, utile pour l'initialisation
     */
    void init(ControllerRegistry registry);
}
