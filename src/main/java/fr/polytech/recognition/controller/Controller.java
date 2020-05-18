package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.model.Model;
import fr.polytech.recognition.view.View;

public abstract class Controller<V extends View, M extends Model> {
    protected final Router rooter;
    protected V currentView;
    protected M model;

    public Controller(Router rooter, M model) {
        this.rooter = rooter;
        this.model = model;
    }

    public abstract void init();

    protected void setView(V currentView) {
        this.currentView = currentView;
    }

    public void showView() {
        this.currentView.display();
    }

    public void disposeView() {
        this.currentView.dispose();
    }
}
