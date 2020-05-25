package fr.polytech.recognition.context;

import fr.polytech.recognition.ai.Classifier;
import fr.polytech.recognition.ai.RecognitionResult;
import fr.polytech.recognition.dao.Identifiable;
import fr.polytech.recognition.event.EventHandler;
import fr.polytech.recognition.event.events.ClassificationFinishedEvent;
import fr.polytech.recognition.event.events.ImageChoosenEvent;
import fr.polytech.recognition.controller.infra.Router;
import lombok.AllArgsConstructor;

import javax.imageio.ImageIO;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class ClassifierContext<L, O extends Identifiable> {

    private Classifier<L> classifier;

    private Function<RecognitionResult<L>, List<O>> transformationMethod;

    @EventHandler
    public void onImageChoose(ImageChoosenEvent event) {
        try {
            RecognitionResult<L> prediction = classifier.prediction(ImageIO.read(event.getFileSelected()));
            Router.getInstance().getEventManager()
                    .dispatch(new ClassificationFinishedEvent<O>(transformationMethod.apply(prediction)));
        } catch (Exception e) {
            e.printStackTrace();
            Router.getInstance().getEventManager().dispatch(new ClassificationFinishedEvent(e));
        }
    }
}
