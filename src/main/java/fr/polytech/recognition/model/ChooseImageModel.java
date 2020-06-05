package fr.polytech.recognition.model;

import fr.polytech.recognition.controller.infra.di.Injectable;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Injectable
public class ChooseImageModel implements Model {
    @Getter @Setter
    private File fileSelected;
}
