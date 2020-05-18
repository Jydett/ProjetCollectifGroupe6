package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.controller.routingevent.EventHandler;
import fr.polytech.recognition.model.ArticleCaractModel;
import fr.polytech.recognition.view.ArticleCaractView;

@ControllerRegistration(name = "articleCaract")
public class ArticleCaractController extends Controller<ArticleCaractView, ArticleCaractModel> {


    public ArticleCaractController(Router rooter, ArticleCaractModel model) {
        super(rooter, model);
    }

    public void init() {
        setView(rooter.createView(ArticleCaractView.class));
    }

}
