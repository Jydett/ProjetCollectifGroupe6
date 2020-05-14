package fr.polytech.recognition.dao.articletype;

import fr.polytech.recognition.model.ArticleType;

public interface ArticleTypeDao {
    void saveOrCreate(ArticleType toSave);
    void remove(ArticleType toSave);
}
