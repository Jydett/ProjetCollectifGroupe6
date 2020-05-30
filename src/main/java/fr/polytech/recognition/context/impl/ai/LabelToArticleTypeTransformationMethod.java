package fr.polytech.recognition.context.impl.ai;

import fr.polytech.recognition.ai.Prediction;
import fr.polytech.recognition.ai.RecognitionResult;
import fr.polytech.recognition.context.TransformationMethod;
import fr.polytech.recognition.controller.infra.di.Inject;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.dao.article.impl.HibernateArticleDao;
import fr.polytech.recognition.model.database.Article;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.tensorflow.op.sparse.SparseReduceMax;

import java.util.*;

public class LabelToArticleTypeTransformationMethod implements TransformationMethod<String, Article> {

    @Inject
    private ArticleDao dao;

    @Override
    public List<Article> apply(RecognitionResult<String> recognitionResult) {
        ArrayList<Article> foundArticle = new ArrayList<Article>();

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        HibernateArticleDao dao = new HibernateArticleDao(session);
        Collection<Article> articles = dao.getAll();

        Iterator<Prediction<String>> predIt = recognitionResult.getPredictions().iterator();
        // for every label
        while (predIt.hasNext()) {
            Prediction<String> prediction = predIt.next();
            // remove bad predictions
            if (prediction.getValue() < 0.3) continue;
            Iterator<Article> articleIterator = articles.iterator();
            // for every article type
            while(articleIterator.hasNext()) {
                Article article = articleIterator.next();
                String articleName = article.getName();
                System.out.println("db article name : " + articleName);
                // if article type matches label
                if (prediction.getObject().contains(articleName)) foundArticle.add(article);
            }
        }


        return foundArticle;//TODO
    }
}
