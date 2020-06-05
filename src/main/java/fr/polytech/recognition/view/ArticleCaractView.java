package fr.polytech.recognition.view;

import fr.polytech.recognition.model.database.Article;

import java.io.File;

public interface ArticleCaractView extends View {

    public void populateArticleTab(Article artSelected);
    public void setImageCharacter(File fileSelected);
}
