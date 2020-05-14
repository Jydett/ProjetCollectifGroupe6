package fr.polytech.recognition.controller.infra.impl;

import fr.polytech.recognition.controller.infra.context.Context;
import fr.polytech.recognition.view.swing.FrameContainer;
import lombok.Getter;

public class SwingContext implements Context {
    @Getter
    private FrameContainer container;

    public SwingContext() {
        container = new FrameContainer();
    }

    @Override
    public void init() {
        container.init();
    }
}
