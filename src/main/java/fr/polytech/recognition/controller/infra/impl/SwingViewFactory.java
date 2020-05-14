package fr.polytech.recognition.controller.infra.impl;

import fr.polytech.recognition.controller.ChooseImageController;
import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.controller.infra.ViewFactory;
import fr.polytech.recognition.view.swing.SwingChooseImageView;
import fr.polytech.recognition.view.swing.SwingImageChoosenView;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class SwingViewFactory implements ViewFactory {

    private final SwingViewContext context;
    private final ControllerRegistry controllerRegistry;

    @Override
    public <V> V getView(Class<V> viewClass) {
        switch (viewClass.getSimpleName()) {
            //TODO enum ?
            case "ChooseImageView" : {
                Optional<ChooseImageController> optController = controllerRegistry.getController("chooseImage");
                return (V) new SwingChooseImageView(context.getContainer(), optController.orElseThrow(() -> new IllegalArgumentException("No controller found")));
            }
            case "ImageChosenView" : {
                Optional<ImageChosenController> optController = controllerRegistry.getController("imageChosen");
                return (V) new SwingImageChoosenView(context.getContainer(), optController.orElseThrow(() -> new IllegalArgumentException("No controller found")));
            }
            default: throw new IllegalArgumentException("unkown view");
        }
    }
}
