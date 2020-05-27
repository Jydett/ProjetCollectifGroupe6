package fr.polytech.recognition.context.impl.view;

import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.context.ContextHolder;
import fr.polytech.recognition.view.swing.SwingViewTabbedContext;
import lombok.Getter;

@Getter
public class SwingContextHolder implements ContextHolder {

    private SwingViewFactory viewFactory;
    private SwingViewTabbedContext currentContext;

    public void init(ControllerRegistry registry) {
        currentContext = new SwingViewTabbedContext();
        viewFactory = new SwingViewFactory(registry);
    }
}
