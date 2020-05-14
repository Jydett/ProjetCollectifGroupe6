package fr.polytech.recognition.controller.infra;

public interface ViewFactory {

    <V> V getView(Class<V> viewClass);
}
