package fr.polytech.recognition.context;

import fr.polytech.recognition.ai.Classifier;
import fr.polytech.recognition.ai.RecognitionResult;
import fr.polytech.recognition.dao.Identifiable;
import fr.polytech.recognition.event.EventHandler;
import fr.polytech.recognition.event.events.ClassificationFinishedEvent;
import fr.polytech.recognition.event.events.ImageChoosenEvent;
import fr.polytech.recognition.controller.infra.Router;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.imageio.ImageIO;

@AllArgsConstructor
public class ClassifierContext<L, O extends Identifiable> {

    private Classifier<L> classifier;

    @Getter
    private TransformationMethod<L, O> transformationMethod;

    @EventHandler
    public void onImageChoose(ImageChoosenEvent event) {
        try {
            RecognitionResult<L> prediction = classifier.prediction(ImageIO.read(event.getFileSelected()));
            Router.getInstance().getEventManager()
                    .dispatch(new ClassificationFinishedEvent<>(transformationMethod.apply(prediction)));
        } catch (Exception e) {
            e.printStackTrace();
            Router.getInstance().getEventManager().dispatch(new ClassificationFinishedEvent(e));
        }
    }
}
