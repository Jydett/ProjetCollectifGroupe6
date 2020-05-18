package fr.polytech.recognition.context.impl;

import fr.polytech.recognition.context.ViewContext;
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
