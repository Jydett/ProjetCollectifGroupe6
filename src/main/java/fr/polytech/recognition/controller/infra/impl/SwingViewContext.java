package fr.polytech.recognition.controller.infra.impl;

import fr.polytech.recognition.controller.infra.context.ViewContext;
import fr.polytech.recognition.view.swing.FrameContainer;
import lombok.Getter;

public class SwingViewContext implements ViewContext {
    @Getter
    private final FrameContainer container;

    public SwingViewContext() {
        container = new FrameContainer();
    }

    @Override
    public void init() {
        container.init();
    }
}
