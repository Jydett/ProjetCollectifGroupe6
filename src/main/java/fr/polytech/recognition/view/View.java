package fr.polytech.recognition.view;

public interface View {

    void initialize();

    default void dispose() {

    }

    String getTitle();
}
