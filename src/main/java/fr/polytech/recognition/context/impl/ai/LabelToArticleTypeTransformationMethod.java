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

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Transforme une liste de label en une liste d'article
 * @see fr.polytech.recognition.context.TransformationMethod
 */
public class LabelToArticleTypeTransformationMethod implements TransformationMethod<String, Article> {

    @Inject
    private ArticleDao dao;

    /**
     * Lance la transformation
     */
    @Override
    public Map<Article, Float> apply(RecognitionResult<String> recognitionResult) {
        return Collections.emptyMap();//TODO
    }
}
