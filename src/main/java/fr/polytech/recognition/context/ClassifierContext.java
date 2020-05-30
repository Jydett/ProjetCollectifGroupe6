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

/**
 * Contexte d'execution d'un algorithme de reconnaissance
 *
 * Le classificateur retourne en sortie un type brut et la méthode de
 * transformation permet de modifier ces résultats en données utilisable par l'application
 *
 * Par exemple le classificateur peut retourner une liste de label associé d'une probabilité,
 * la méthode de transformation va la transformer en une liste d'object tirés de la base de
 * donnée.
 *
 * @param <L> le type de sortie du classificateur
 * @param <O> le type de sortie apres la transformation des résultats
 */
@AllArgsConstructor
public class ClassifierContext<L, O extends Identifiable> {

    /**
     * Le classificateur choisis
     */
    private Classifier<L> classifier;

    /**
     * La méthode de transformation des résultat choisie
     */
    @Getter
    private TransformationMethod<L, O> transformationMethod;

    /**
     * Appelé lorsqu'une image a été choisie.
     * Utilise le classificateur pour analyser l'image et transforme le résultat
     * avant de l'envoyer via un {@see ClassificationFinishedEvent}
     * @param event un événement contenant l'image choisis
     */
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
