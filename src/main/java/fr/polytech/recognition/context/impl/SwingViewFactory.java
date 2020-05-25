package fr.polytech.recognition.context.impl;

import fr.polytech.recognition.controller.ArticleCaractController;
import fr.polytech.recognition.controller.ChooseImageController;
import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.context.ViewFactory;
import fr.polytech.recognition.view.swing.pages.SwingArticleCaractView;
import fr.polytech.recognition.view.swing.pages.SwingChooseImageView;
import fr.polytech.recognition.view.swing.pages.SwingImageChosenView;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.function.Supplier;

@AllArgsConstructor
public class SwingViewFactory implements ViewFactory {

    private final ControllerRegistry controllerRegistry;
    private final Supplier<IllegalArgumentException> noControllerFoundExceptionSupplier = () -> new IllegalArgumentException("No controller found");

    @Override
    public <V> V getView(Class<V> viewClass) {
        switch (viewClass.getSimpleName()) {
            //TODO enum ?
            case "ChooseImageView" : {
                Optional<ChooseImageController> optController = controllerRegistry.getController("chooseImage");
                return (V) new SwingChooseImageView(optController.orElseThrow(noControllerFoundExceptionSupplier));
            }
            case "ImageChosenView" : {
                Optional<ImageChosenController> optController = controllerRegistry.getController("imageChosen");
                return (V) new SwingImageChosenView(optController.orElseThrow(noControllerFoundExceptionSupplier));
            }
            case "ArticleCaractView" : {
                Optional<ArticleCaractController> optController = controllerRegistry.getController("articleCaract");
                return (V) new SwingArticleCaractView(optController.orElseThrow(noControllerFoundExceptionSupplier));
            }
            default: throw new IllegalArgumentException("Unknown view " + viewClass.getSimpleName());
        }
    }
}
