package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.event.EventHandler;
import fr.polytech.recognition.event.events.ClassificationFinishedEvent;
import fr.polytech.recognition.event.events.ImageChoosenEvent;
import fr.polytech.recognition.model.ImageChosenModel;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.view.ImageChosenView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ControllerRegistration(name = "imageChosen")
public class ImageChosenController extends Controller<ImageChosenView, ImageChosenModel> {

    private File fileSelected;

    public ImageChosenController(Router rooter, ImageChosenModel model) {
        super(rooter, model);
    }

    /**
     * Créer la vue de ImageChosenView
     */
    protected void initialize() {
        setView(rooter.createView(ImageChosenView.class));
    }

    /**
     * Event déclenché quand on a le resultat de la reconnaissance
     * @param event Contient les résultat de la réconnaissance : Res est un HashMap contient les articles et leur probalilités.
     */
    @EventHandler
    public void onRecognitionResult(ClassificationFinishedEvent<Article> event) {
        System.out.println("Resultat recu : " + event.toString());
        getCurrentView().afficherArticleList(event.getRes());
    }

    /**
     * Event déclenché quand on choisit une image sur la page ChooseImage. Appelle setImageCharacter()
     * @param event Contient le fichier selectionné sur la page ChooseImage
     */
    @EventHandler
    public void onImageChosen(ImageChoosenEvent event) {
        getCurrentView().setImageChosen(event.getFileSelected());
    }

    public String getSelectedImage() {
        return fileSelected == null ? null : fileSelected.getAbsolutePath();
    }


}
