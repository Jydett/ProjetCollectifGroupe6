package fr.polytech.recognition.context.impl;

import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.context.ContextHolder;
import lombok.Getter;

@Getter
public class SwingContextHolder implements ContextHolder {

    private SwingViewFactory viewFactory;
    private SwingViewContext currentContext;

    public void init(ControllerRegistry registry) {
        currentContext = new SwingViewContext();
        viewFactory = new SwingViewFactory(currentContext, registry);
    }
}
