package fr.polytech.recognition.context;

import fr.polytech.recognition.ai.RecognitionResult;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Transforme un résultat de reconnaissance en
 * un dictionnaire associant à un élément du modèle, une probabilité de reconaissance
 * @param <L> le type du résultat de la reconaissance
 * @param <O> le type de l'élément du modèle
 */
public interface TransformationMethod<L, O> extends Function<RecognitionResult<L>, Map<O, Float>> {
}
