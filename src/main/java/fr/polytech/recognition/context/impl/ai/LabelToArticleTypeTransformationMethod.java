package fr.polytech.recognition.context.impl.ai;

import fr.polytech.recognition.ai.RecognitionResult;
import fr.polytech.recognition.context.TransformationMethod;
import fr.polytech.recognition.controller.infra.di.Inject;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.model.database.Article;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LabelToArticleTypeTransformationMethod implements TransformationMethod<String, Article> {

    @Inject
    private ArticleDao dao;

    @Override
    public Map<Article, Float> apply(RecognitionResult<String> recognitionResult) {
        return Collections.emptyMap();//TODO
    }
}
