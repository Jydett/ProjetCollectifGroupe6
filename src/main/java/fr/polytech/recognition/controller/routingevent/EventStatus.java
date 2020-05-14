package fr.polytech.recognition.controller.routingevent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventStatus {
    private final boolean canceled;
    private final String message;
}
