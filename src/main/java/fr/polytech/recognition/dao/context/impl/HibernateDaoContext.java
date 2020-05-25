package fr.polytech.recognition.dao.context.impl;

import fr.polytech.recognition.controller.infra.di.InjectionManager;
import fr.polytech.recognition.dao.article.impl.HibernateArticleDao;
import fr.polytech.recognition.dao.context.DaoContext;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateDaoContext implements DaoContext {

    @Override
    public void init() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        InjectionManager.register("ArticleDao", new HibernateArticleDao(session));
    }
}
