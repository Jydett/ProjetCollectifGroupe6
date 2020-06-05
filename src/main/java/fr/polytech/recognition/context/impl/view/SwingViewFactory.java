package fr.polytech.recognition.context.impl.view;

import fr.polytech.recognition.controller.ArticleCaractController;
import fr.polytech.recognition.controller.ChooseImageController;
import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.context.ViewFactory;
import fr.polytech.recognition.view.View;
import fr.polytech.recognition.view.swing.pages.SwingArticleCaractView;
import fr.polytech.recognition.view.swing.pages.SwingChooseImageView;
import fr.polytech.recognition.view.swing.pages.SwingImageChosenView;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Une fabrique pour créer des {@see SwingView}
 * @see fr.polytech.recognition.context.ViewFactory
 */
public class SwingViewFactory implements ViewFactory {

    /**
     * Un dictionnaire des vues pouvant être instancié par cette fabrique
     */
    private final HashMap<String, Supplier<View>> viewMaps;

    /**
     * Initialise cette fabrique
     * @param controllerRegistry le registre des controlleurs
     * @throws IllegalArgumentException si un des controlleurs nécessaire à la fabrication d'une des vues
     * supportés par cette fabrique n'est pas enregistré dans le registre des controlleurs
     */
    public SwingViewFactory(ControllerRegistry controllerRegistry) {
        viewMaps = new HashMap<>();
        Supplier<IllegalArgumentException> noControllerFound = () -> new IllegalArgumentException("No controller found");

        final ChooseImageController chooseImageController = controllerRegistry.<ChooseImageController>getController("chooseImage").orElseThrow(noControllerFound);
        viewMaps.put("ChooseImageView", () -> new SwingChooseImageView(chooseImageController));

        final ImageChosenController imageChosenController = controllerRegistry.<ImageChosenController>getController("imageChosen").orElseThrow(noControllerFound);
        viewMaps.put("ImageChosenView", () -> new SwingImageChosenView(imageChosenController));

        final ArticleCaractController articleCaractController = controllerRegistry.<ArticleCaractController>getController("articleCaract").orElseThrow(noControllerFound);
        viewMaps.put("ArticleCaractView", () -> new SwingArticleCaractView(articleCaractController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> V getView(Class<V> viewClass) {
        //noinspection unchecked
        return (V) viewMaps.getOrDefault(viewClass.getSimpleName(), () -> viewNotFound(viewClass)).get();
    }
}
