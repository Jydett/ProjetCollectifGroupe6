package fr.polytech.recognition.context;

import fr.polytech.recognition.ai.RecognitionResult;

import java.util.List;
import java.util.function.Function;

public interface TransformationMethod<L, O> extends Function<RecognitionResult<L>, List<O>> {
}
