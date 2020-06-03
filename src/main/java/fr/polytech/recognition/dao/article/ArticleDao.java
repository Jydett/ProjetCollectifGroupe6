package fr.polytech.recognition.dao.article;

import fr.polytech.recognition.model.database.Article;

import java.util.Collection;
import java.util.Optional;
/**
 * Interface ArticleDao
 *
 * assure la gestion des données de la table Article
 *
 */
public interface ArticleDao {
    /**
     * Récupère un article en fonction du type d'article passé en paramètre
     * @param articleType un type d'article
     * @return l'article du type passé en paramètre
     */
    Optional<Article> getArticleOfType(String articleType);

    /**
     * Récupère un article en fonction de l'id passé en paramètre
     * @param id un id
     * @return l'article de l'id en paramètre
     */
    Optional<Article> getById(Long id);

    /**
     * Ajoute un article dans la BdD
     * @param toSave un article
     */
    void saveOrCreate(Article toSave);

    /**
     * Supprime un article de la BdD
     * @param toSave un article
     */
    void remove(Article toSave);
}
