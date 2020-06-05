package fr.polytech.recognition.event.events;

import fr.polytech.recognition.dao.Identifiable;
import fr.polytech.recognition.event.Event;
import lombok.Getter;

import java.util.Map;

@Getter
public class ClassificationFinishedEvent<Bean extends Identifiable> implements Event {
    private final Map<Bean, Float> res;
    private final Throwable error;

    public ClassificationFinishedEvent(Map<Bean, Float> res) {
        this.res = res;
        this.error = null;
    }

    public ClassificationFinishedEvent(Throwable error) {
        this.error = error;
        this.res = null;
    }

    @Override
    public String toString() {
        return "ClassificationFinishedEvent{" +
                "res=" + res +
                ", error=" + error +
                '}';
    }
}
