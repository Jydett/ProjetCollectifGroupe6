package fr.polytech.recognition.context;


public interface ViewFactory {

    <V> V getView(Class<V> viewClass);
}
