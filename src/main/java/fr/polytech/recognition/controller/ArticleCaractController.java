package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.event.EventHandler;
import fr.polytech.recognition.event.events.ArticleClickedEvent;
import fr.polytech.recognition.event.events.ClassificationFinishedEvent;
import fr.polytech.recognition.model.ArticleCaractModel;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.view.ArticleCaractView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

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
    public void onRecognitionResult(ArticleClickedEvent event) {
        getCurrentView().populateArticleTab(event.getArtSelected());
    }

}
