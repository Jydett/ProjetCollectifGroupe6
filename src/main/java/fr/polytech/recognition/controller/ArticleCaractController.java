package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.event.EventHandler;
import fr.polytech.recognition.event.events.ArticleClickedEvent;
import fr.polytech.recognition.event.events.ImageChoosenEvent;
import fr.polytech.recognition.model.ArticleCaractModel;
import fr.polytech.recognition.view.ArticleCaractView;

@ControllerRegistration(name = "articleCaract")
public class ArticleCaractController extends Controller<ArticleCaractView, ArticleCaractModel> {


    public ArticleCaractController(Router rooter, ArticleCaractModel model) {
        super(rooter, model);
    }

    protected void initialize() {
        setView(rooter.createView(ArticleCaractView.class));
    }

    /**
     * Event déclenché quand on clique sur un article sur la page ImageChosen. Appelle populateArticleTab
     * @param event Contient l'article selectionné sur la page ImageChosen
     */
    @EventHandler
    public void onArticleClicked(ArticleClickedEvent event) {
        getCurrentView().populateArticleTab(event.getArtSelected());
    }

    /**
     * Event déclenché quand on choisit une image sur la page ChooseImage. Appelle setImageCharacter()
     * @param event Contient le fichier selectionné sur la page ChooseImage
     */
    @EventHandler
    public void onImageChosen(ImageChoosenEvent event) {
        getCurrentView().setImageCharacter(event.getFileSelected());
    }

}
