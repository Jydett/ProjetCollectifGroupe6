package fr.polytech.recognition.view.swing;

import com.formdev.flatlaf.FlatLightLaf;
import lombok.Getter;

import javax.swing.*;

public class FrameContainer {//TODO remplacer en swing context
    @Getter
    public JFrame frame;

    public FrameContainer() {
        FlatLightLaf.install();
        this.frame = new JFrame();
    }

    public void init() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
