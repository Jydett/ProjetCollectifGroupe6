package fr.polytech.recognition.ai;

import java.util.ArrayList;

/**
 * Interface for the classes containing the result of predicitons (usually a list of Prediction objects)
 * @param <O> The parameter (see prediciton class) representing the object associated with the prediction score
 */
public interface RecognitionResult<O> {

    Prediction<O> getBestMatch();

    void sortPredictions(boolean highToLow);

    ArrayList<Prediction<O>> getPredictions();
}
