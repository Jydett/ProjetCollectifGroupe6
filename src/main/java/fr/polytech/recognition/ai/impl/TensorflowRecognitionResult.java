package fr.polytech.recognition.ai.impl;
import fr.polytech.recognition.ai.Prediction;
import fr.polytech.recognition.ai.RecognitionResult;
import org.tensorflow.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the result of a prediction, contains methods to exploit the result
 */
public class TensorflowRecognitionResult implements RecognitionResult<String> {
    /**
     * The predictions
     */
    private ArrayList<Prediction<String>> predictions;

    /**
     *
     * @param result Expected to contain a single result on the first row
     * @param labels the labels associated with the model
     */
    public TensorflowRecognitionResult(Tensor result, List<String> labels) {

        predictions = new ArrayList<>();
        float[][] predictionResult = new float[1][(int)result.shape()[1]];
        result.copyTo(predictionResult);
        float[] predictionValues = predictionResult[0];

        for (int labelIndex= 0 ; labelIndex<predictionValues.length && labelIndex<labels.size() ; labelIndex++) {
            predictions.add(new Prediction<>(labels.get(labelIndex), predictionValues[labelIndex]));
        }
    }

    /**
     *
     * @return the label with the highest score, if two labels have an equal best score, returns the last in the list
     */
    @Override
    public Prediction<String> getBestMatch() {
        return Collections.max(predictions);
    }

    /**
     * Sort predictions from highest value to lowest
     * @param highToLow true if sorting from highest to lowest score (descending) false else
     */
    @Override
    public void sortPredictions(boolean highToLow) {
        if (highToLow) Collections.sort(predictions, Collections.reverseOrder());
        else Collections.sort(predictions);
    }

    /**
     *
     * @return the list of predicted values ranging from 0 to 1
     */
    @Override
    public ArrayList<Prediction<String>> getPredictions() {
        return predictions;
    }

    @Override
    public String toString() {
        return predictions.toString();
    }

}
