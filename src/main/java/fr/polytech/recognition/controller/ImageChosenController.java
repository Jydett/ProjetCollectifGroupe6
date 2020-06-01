package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.event.EventHandler;
import fr.polytech.recognition.event.events.ClassificationFinishedEvent;
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

    public void init() {
        setView(rooter.createView(ImageChosenView.class));
    }

    @EventHandler
    public void onRecognitionResult(ClassificationFinishedEvent<Article> event) {
        System.out.println("Resultat recu : " + event.toString());
        getCurrentView().afficherArticleList(event.getRes());
    }

    public String getSelectedImage() {
        return fileSelected == null ? null : fileSelected.getAbsolutePath();
    }


}
