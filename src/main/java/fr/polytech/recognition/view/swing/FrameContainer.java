package fr.polytech.recognition.view.swing;

import lombok.Getter;

import javax.swing.*;

public class FrameContainer {
    @Getter
    public JFrame frame;

    public FrameContainer() {
        this.frame = new JFrame();
    }

    public void init() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
