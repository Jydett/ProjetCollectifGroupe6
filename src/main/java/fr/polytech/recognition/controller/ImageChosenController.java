package fr.polytech.recognition.controller;

import fr.polytech.recognition.controller.infra.ControllerRegistration;
import fr.polytech.recognition.controller.infra.Router;
import fr.polytech.recognition.controller.routingevent.EventHandler;
import fr.polytech.recognition.controller.routingevent.events.ImageChoosenEvent;
import fr.polytech.recognition.model.ImageChosenModel;
import fr.polytech.recognition.view.ImageChosenView;

import java.io.File;

@ControllerRegistration(name = "imageChosen")
public class ImageChosenController extends Controller<ImageChosenView, ImageChosenModel> {

    private File fileSelected;

    public ImageChosenController(Router rooter, ImageChosenModel model) {
        super(rooter, model);
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
