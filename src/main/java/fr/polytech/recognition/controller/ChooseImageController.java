package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.controller.infra.di.Inject;
import fr.polytech.recognition.controller.routingevent.events.ImageChoosenEvent;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.model.ChooseImageModel;
import fr.polytech.recognition.view.ChooseImageView;

import java.awt.event.ActionEvent;
import java.io.File;

@ControllerRegistration(name = "chooseImage")
public class ChooseImageController extends Controller<ChooseImageView, ChooseImageModel> {

    @Inject
    public ArticleDao articleDao;

    public ChooseImageController(Router rooter, ChooseImageModel model) {
        super(rooter, model);
    }

    public void init() {
        setView(rooter.createView(ChooseImageView.class));
    }

    public void onImportButtonClicked(ActionEvent actionEvent) {
        imageChoosen(currentView.chooseImage());
    }

    public void imageChoosen(File selectedFile) {
        rooter.dispatchEvent(new ImageChoosenEvent(selectedFile));
        rooter.nextController("imageChosen");
    }
}
