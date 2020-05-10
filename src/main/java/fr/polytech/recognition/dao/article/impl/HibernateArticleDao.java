package fr.polytech.recognition.dao.article.impl;

import fr.polytech.recognition.dao.HibernateDao;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.model.Article;
import fr.polytech.recognition.model.ArticleType;
import org.hibernate.Session;

import java.util.Collection;

public class HibernateArticleDao extends HibernateDao<Long, Article> implements ArticleDao {

    public HibernateArticleDao(Session hibernateSession) {
        super(hibernateSession);
    }

    @Override
    public Collection<Article> getAllArticlesOfType(ArticleType articleType) {
        return hibernateSession.createQuery("select a from Article a where a.type is not null and a.type.id = :articleTypeId", Article.class)
                .setParameter("articleTypeId", articleType.getId())
                .getResultList();
    }
}
