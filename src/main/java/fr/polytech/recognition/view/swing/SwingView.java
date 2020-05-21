package fr.polytech.recognition.view.swing;

import javax.swing.*;

public abstract class SwingView extends JPanel {

    public abstract String getTitle();

    public Icon getIcon() {
        return null;
    }
}
