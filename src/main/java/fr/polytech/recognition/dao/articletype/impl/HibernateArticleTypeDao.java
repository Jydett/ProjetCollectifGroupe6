package fr.polytech.recognition.dao.articletype.impl;

import fr.polytech.recognition.dao.HibernateDao;
import fr.polytech.recognition.dao.articletype.ArticleTypeDao;
import fr.polytech.recognition.model.database.ArticleType;
import org.hibernate.Session;

public class HibernateArticleTypeDao extends HibernateDao<Long, ArticleType> implements ArticleTypeDao {

    public HibernateArticleTypeDao(Session hibernateSession) {
        super(hibernateSession);
    }

}
