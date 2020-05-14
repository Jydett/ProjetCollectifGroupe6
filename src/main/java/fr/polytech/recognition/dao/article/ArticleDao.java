package fr.polytech.recognition.dao.article;

import fr.polytech.recognition.model.Article;
import fr.polytech.recognition.model.ArticleType;

import java.util.Collection;
import java.util.Optional;

public interface ArticleDao {
    Collection<Article> getAllArticlesOfType(ArticleType articleType);
    Optional<Article> getById(Long id);
    void saveOrCreate(Article toSave);
    void remove(Article toSave);
}