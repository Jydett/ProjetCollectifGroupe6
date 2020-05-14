package fr.polytech.recognition.view.swing;

import fr.polytech.recognition.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;

@AllArgsConstructor
public abstract class SwingView extends JPanel implements View {

    protected JFrame frame;

    @Override
    public void display() {
        frame.setContentPane(this);
        initialize();
        frame.repaint();
    }

    protected abstract void initialize();

    @Override
    public void dispose() {

    }
}
