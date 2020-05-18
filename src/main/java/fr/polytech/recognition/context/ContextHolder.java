package fr.polytech.recognition.context;

import fr.polytech.recognition.controller.infra.ControllerRegistry;

public interface ContextHolder {

    ViewContext getCurrentContext();

    ViewFactory getViewFactory();

    void init(ControllerRegistry registry);
}
