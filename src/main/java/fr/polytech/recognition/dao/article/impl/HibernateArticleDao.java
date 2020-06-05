package fr.polytech.recognition.dao.article.impl;

import fr.polytech.recognition.dao.HibernateDao;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.model.database.Article;
import org.hibernate.Session;

import java.util.Optional;
/**
 * classe HibernateArticleDao
 *
 * Dao qui assure la gestion des articles présents dans la BdD
 *
 * ajouts, suppression, récupération d'un ou plusieurs articles
 *
 */
public class HibernateArticleDao extends HibernateDao<Long, Article> implements ArticleDao {

    /**
     * Initialise le dao pour la gestion des articles avec la session passé en paramètre
     *
     * @param hibernateSession une session hibernate
     */
    public HibernateArticleDao(Session hibernateSession) {
        super(hibernateSession);
    }

    /**
     * Récupère un article en fonction du type d'article passé en paramètre
     *
     * @param articleType un type d'article
     * @return l'article du type passé en paramètre
     */
    @Override
    public Optional <Article> getArticleOfType(String articleType) {
        return hibernateSession.createQuery("select a from Article a where a is not null and a.name = :articleTypeId", Article.class)
                .setParameter("articleTypeId", articleType)
                .getResultList()
                .stream()
                .findFirst();
    }

}
