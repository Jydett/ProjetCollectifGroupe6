package fr.polytech.recognition.context.impl.view;

import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.context.ViewContextHolder;
import fr.polytech.recognition.view.swing.SwingViewTabbedContext;
import lombok.Getter;

@Getter
public class SwingViewContextHolder implements ViewContextHolder {

    private SwingViewFactory viewFactory;
    private SwingViewTabbedContext currentContext;

    public void init(ControllerRegistry registry) {
        currentContext = new SwingViewTabbedContext();
        viewFactory = new SwingViewFactory(registry);
    }
}
