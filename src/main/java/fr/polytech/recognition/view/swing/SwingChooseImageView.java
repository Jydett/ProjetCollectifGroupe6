package fr.polytech.recognition.view.swing;

import fr.polytech.recognition.controller.ChooseImageController;
import fr.polytech.recognition.view.ChooseImageView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class SwingChooseImageView extends SwingView implements ChooseImageView {
    private final ChooseImageController controller;
    private final JFileChooser jFileChooser = new JFileChooser();
    private static final FileNameExtensionFilter IMG_FILTER = new FileNameExtensionFilter(
            "JPG & JPEG Images", "jpg", "jpeg");

    public SwingChooseImageView(FrameContainer container, ChooseImageController controller) {
        super(container.getFrame());
        this.controller = controller;
    }

    protected void initialize() {
        frame.setTitle("Application");
        setLayout(new BorderLayout());
        JButton importImage = new JButton("Importer image");
        importImage.addActionListener(controller::onImportButtonClicked);
        add(importImage, BorderLayout.CENTER);
    }

    @Override
    public File chooseImage() {
        jFileChooser.setFileFilter(IMG_FILTER);
        int res = jFileChooser.showDialog(null, "Approve");
        if (res == JFileChooser.APPROVE_OPTION) {
            return jFileChooser.getSelectedFile();
        }
        return null;
    }
}
