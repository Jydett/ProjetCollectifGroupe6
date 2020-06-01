package fr.polytech.recognition.view;

import fr.polytech.recognition.model.database.Article;

import java.util.List;
import java.util.Map;

public interface ImageChosenView extends View {
    void afficherArticleList(Map<Article, Float> res);
}
