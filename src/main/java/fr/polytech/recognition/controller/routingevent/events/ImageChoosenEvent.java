package fr.polytech.recognition.controller.routingevent.events;

import fr.polytech.recognition.controller.routingevent.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
@Getter
public class ImageChoosenEvent implements Event {

    private final File fileSelected;
}
