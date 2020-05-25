package fr.polytech.recognition.ai;

import java.util.ArrayList;

public interface RecognitionResult<O> {

    Prediction<O> getBestMatch();

    void sortPredictions(boolean highToLow);

    ArrayList<Prediction<O>> getPredictions();
}
