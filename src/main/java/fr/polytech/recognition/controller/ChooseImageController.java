package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.Controller;
import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.controller.infra.di.Inject;
import fr.polytech.recognition.controller.routingevent.ImageChoosenEvent;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.view.ChooseImageView;

import java.io.File;

@ControllerRegistration(name = "chooseImage")
public class ChooseImageController extends Controller<ChooseImageView> {

    @Inject
    public ArticleDao articleDao;

    public ChooseImageController(Router rooter) {
        super(rooter);
    }

    public void init() {
        setView(rooter.createView(ChooseImageView.class));
    }

    public void imageChoosen(File selectedFile) {
        rooter.dispatchEvent(new ImageChoosenEvent(selectedFile));
        rooter.nextController("imageChosen");
    }
}
