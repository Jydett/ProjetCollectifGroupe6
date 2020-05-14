package fr.polytech.recognition.controller.infra;

import fr.polytech.recognition.view.View;

public abstract class Controller<V extends View> {
    protected final Router rooter;
    protected V currentView;

    public Controller(Router rooter) {
        this.rooter = rooter;
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
