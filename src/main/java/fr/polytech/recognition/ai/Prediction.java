package fr.polytech.recognition.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
