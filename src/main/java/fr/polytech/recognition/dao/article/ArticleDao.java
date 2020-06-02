package fr.polytech.recognition.dao.article;

import fr.polytech.recognition.model.database.Article;

import java.util.Collection;
import java.util.Optional;
/**
 * Interface assurant la gestion des données de la table Article
 *
 */
public interface ArticleDao {
    Optional<Article> getArticleOfType(String articleType);
    Optional<Article> getById(Long id);
    void saveOrCreate(Article toSave);
    void remove(Article toSave);
}
