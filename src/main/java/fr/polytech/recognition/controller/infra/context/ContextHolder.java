package fr.polytech.recognition.controller.infra.context;

import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.controller.infra.ViewFactory;

public interface ContextHolder {

    Context getCurrentContext();

    ViewFactory getViewFactory();

    void init(ControllerRegistry registry);
}
