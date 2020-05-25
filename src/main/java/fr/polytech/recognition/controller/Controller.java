package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.model.Model;
import fr.polytech.recognition.view.View;
import lombok.Getter;

public abstract class Controller<V extends View, M extends Model> {
    @Getter
    protected final Router rooter;
    @Getter
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

    public void disposeView() {
        this.currentView.dispose();
    }

    public boolean canExit() {
        return true;
    }

    public void exit() {
    }
}
