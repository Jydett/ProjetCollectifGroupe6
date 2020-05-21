package fr.polytech.recognition.controller.event.events;

import fr.polytech.recognition.controller.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
@Getter
public class ImageChoosenEvent implements Event {

    private final File fileSelected;
}
