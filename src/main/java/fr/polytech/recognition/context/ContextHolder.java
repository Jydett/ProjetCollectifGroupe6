package fr.polytech.recognition.context;

import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.view.ViewContext;

public interface ContextHolder {

    ViewContext getCurrentContext();

    ViewFactory getViewFactory();

    void init(ControllerRegistry registry);
}
