package fr.polytech.recognition.event.events;

import fr.polytech.recognition.dao.Identifiable;
import fr.polytech.recognition.event.Event;
import lombok.Getter;

import java.util.List;

@Getter
public class ClassificationFinishedEvent<Bean extends Identifiable> implements Event {
    private List<Bean> res;
    private Throwable error;

    public ClassificationFinishedEvent(List<Bean> res) {
        this.res = res;
    }

    public ClassificationFinishedEvent(Throwable error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ClassificationFinishedEvent{" +
                "res=" + res +
                ", error=" + error +
                '}';
    }
}
