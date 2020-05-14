package fr.polytech.recognition.view.swing;

import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.view.ImageChosenView;

import javax.swing.*;

public class SwingImageChoosenView extends SwingView implements ImageChosenView {

    private final ImageChosenController controller;

    public SwingImageChoosenView(FrameContainer container, ImageChosenController controller) {
        super(container.getFrame());
        this.controller = controller;
    }

    protected void initialize() {
        frame.setContentPane(new JLabel("Image choisie !" + controller.getSelectedImage()));
    }

}
