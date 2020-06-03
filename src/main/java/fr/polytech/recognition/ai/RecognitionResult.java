package fr.polytech.recognition.ai;

import java.util.ArrayList;

/**
 * Interface for the classes containing the result of predicitons (usually a list of Prediction objects)
 * @param <O> The parameter (see prediciton class) representing the object associated with the prediction score
 */
public interface RecognitionResult<O> {
    /**
     *
     * @return The Peediction associated with the best score
     */
    Prediction<O> getBestMatch();

    /**
     * Sorts the predictions
     * @param highToLow true : sorts from highest to lowest, false : sorts from lowest to highest
     */
    void sortPredictions(boolean highToLow);

    /**
     *
     * @return The list of predictions.
     */
    ArrayList<Prediction<O>> getPredictions();
}
