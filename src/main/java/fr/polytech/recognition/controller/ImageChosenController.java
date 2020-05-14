package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.Controller;
import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.controller.routingevent.EventHandler;
import fr.polytech.recognition.controller.routingevent.ImageChoosenEvent;
import fr.polytech.recognition.view.ImageChosenView;

import java.io.File;

@ControllerRegistration(name = "imageChosen")
public class ImageChosenController extends Controller<ImageChosenView> {

    private File fileSelected;

    public ImageChosenController(Router rooter) {
        super(rooter);
    }

    public void init() {
        setView(rooter.createView(ImageChosenView.class));
    }

    @EventHandler
    public void onImageChosen(ImageChoosenEvent event) {
        fileSelected = event.getFileSelected();
    }

    public String getSelectedImage() {
        return fileSelected == null ? null : fileSelected.getAbsolutePath();
    }
}
