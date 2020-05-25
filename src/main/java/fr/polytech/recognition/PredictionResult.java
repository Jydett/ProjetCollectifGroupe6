package fr.polytech.recognition;
import org.tensorflow.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the result of a prediction, contains methods to exploit the result
 */
public class PredictionResult {
    /**
     * The predictions
     */
    ArrayList<Prediction> predictions;

    /**
     *
     * @param result Expected to contain a single result on the first row
     * @param labels the labels associated with the model
     */
    public PredictionResult(Tensor result, List<String> labels) {

        predictions = new ArrayList<Prediction>();;
        float[][] predictionResult = new float[1][(int)result.shape()[1]];
        result.copyTo(predictionResult);
        float[] predictionValues = predictionResult[0];

        for (int labelIndex= 0 ; labelIndex<predictionValues.length && labelIndex<labels.size() ; labelIndex++) {
            predictions.add(new Prediction(labels.get(labelIndex), predictionValues[labelIndex]));
        }
    }

    /**
     *
     * @return the label with the highest score, if two labels have an equal best score, returns the last in the list
     */
    public Prediction findBestLabel() {
        return Collections.max(predictions);
    }

    /**
     * Sort predictions from highest value to lowest
     * @param HighToLow true if sorting from highest to lowest score (descending) false else
     */
    public void sortPredictions(boolean HighToLow) {
        if (HighToLow) Collections.sort(predictions, Collections.reverseOrder());
        else Collections.sort(predictions);
    }

    /**
     *
     * @return the list of predicted values ranging from 0 to 1
     */
    public ArrayList<Prediction> getPredictions() {
        return predictions;
    }

    @Override
    public String toString() {
        return predictions.toString();
    }

}
