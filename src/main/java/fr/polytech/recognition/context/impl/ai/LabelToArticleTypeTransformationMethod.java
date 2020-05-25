package fr.polytech.recognition.context.impl.ai;

import fr.polytech.recognition.ai.RecognitionResult;
import fr.polytech.recognition.context.TransformationMethod;
import fr.polytech.recognition.controller.infra.di.Inject;
import fr.polytech.recognition.dao.article.ArticleDao;
import fr.polytech.recognition.model.database.Article;

import java.util.Collections;
import java.util.List;

public class LabelToArticleTypeTransformationMethod implements TransformationMethod<String, Article> {

    @Inject
    private ArticleDao dao;

    @Override
    public List<Article> apply(RecognitionResult<String> recognitionResult) {
        return Collections.emptyList();//TODO
    }
}
