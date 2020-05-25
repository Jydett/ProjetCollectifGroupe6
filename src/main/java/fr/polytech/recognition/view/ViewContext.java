package fr.polytech.recognition.view;

import fr.polytech.recognition.controller.Controller;
import fr.polytech.recognition.controller.infra.ControllerRegistry;

public interface ViewContext {
    void init(ControllerRegistry controllers);

    void switchView(Controller view);
}
