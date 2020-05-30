package fr.polytech.recognition.dao.context.impl;

import fr.polytech.recognition.controller.infra.di.InjectionManager;
import fr.polytech.recognition.dao.article.impl.HibernateArticleDao;
import fr.polytech.recognition.dao.context.DaoContext;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Un context de DAOs utilisant Hibernate pour faire persister les données
 */
public class HibernateDaoContext implements DaoContext {

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        InjectionManager.register("ArticleDao", new HibernateArticleDao(session));
    }
}
