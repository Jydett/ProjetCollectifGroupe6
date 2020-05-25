package fr.polytech.recognition.dao.article.impl;

import fr.polytech.recognition.dao.HibernateDao;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.model.database.Article;
import org.hibernate.Session;

import java.util.Collection;
import java.util.Optional;

public class HibernateArticleDao extends HibernateDao<Long, Article> implements ArticleDao {

    public HibernateArticleDao(Session hibernateSession) {
        super(hibernateSession);
    }

    @Override
    public Optional <Article> getArticleOfType(String articleType) {
        return hibernateSession.createQuery("select a from Article a where a is not null and a.name = :articleTypeId", Article.class)
                .setParameter("articleTypeId", articleType)
                .getResultList()
                .stream()
                .findFirst();
    }

}
