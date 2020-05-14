package fr.polytech.recognition;

public class Prediction  implements Comparable<Prediction> {
    public String label;
    public Float value;

    Prediction(String label, float value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public int compareTo(Prediction o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return "[Score : " + value + " Label : " + label + "]";
    }
}
