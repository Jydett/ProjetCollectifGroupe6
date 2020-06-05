package fr.polytech.recognition.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a prediction, object associated with a score ranging from 0 to 1.
 * @param <O> The object to associate with the score
 */
@AllArgsConstructor
@Getter
public class Prediction<O> implements Comparable<Prediction<O>> {
    private final O object;
    private final Float value;

    @Override
    public int compareTo(Prediction<O> o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return "[Score : " + value + " Label : " + object + "]";
    }
}
