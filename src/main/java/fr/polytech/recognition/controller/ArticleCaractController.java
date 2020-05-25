package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.event.events.ImageChoosenEvent;
import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.model.ArticleCaractModel;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.view.ArticleCaractView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ControllerRegistration(name = "articleCaract")
public class ArticleCaractController extends Controller<ArticleCaractView, ArticleCaractModel> {


    public ArticleCaractController(Router rooter, ArticleCaractModel model) {
        super(rooter, model);
    }

    public void init() {
        setView(rooter.createView(ArticleCaractView.class));
    }

    /**
     * Appellé lors du click sur un article dans l'écran ImageChosen. Ajoute les données de l'article selectionné dans le Jtab.
     * @param tabArticle Jtab sur lequel les données sont affichés
     * @param artSelected Article selectionné dans le Jtab d'ImageChosen
     */
    public void populateArticleTab(JTable tabArticle, Article artSelected)
    {
        DefaultTableModel tableMod = (DefaultTableModel) tabArticle.getModel();
        tableMod.setRowCount(0); // Clear the Table of all of his rows

        List<Object> listHeader = new ArrayList<Object>( );
        listHeader.add("Type d'article"); listHeader.add("Nom"); listHeader.add("Prix"); listHeader.add("Lien Vendeur");
        tableMod.addRow(listHeader.toArray());

        List<Object> listTable = new ArrayList<Object>();

        listTable.add(artSelected.getArticleType());
        listTable.add(artSelected.getName());
        listTable.add(artSelected.getPrice());
        listTable.add(artSelected.getVendorLink());

        tableMod.addRow(listTable.toArray());
    }

}
