package tensorflow;

import fr.polytech.recognition.ai.impl.TensorflowRecognitionResult;
import fr.polytech.recognition.ai.impl.TensorflowClassifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class TensorflowTest {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(TensorflowClassifier.class.getResource("/images/test_image.jpg"));
            TensorflowClassifier classificator = new TensorflowClassifier(
                    "/tensorflow_inception_graph.pb",
                    "/imagenet_comp_graph_label_strings.txt",
                    224,224, 117f, 1f
            );
            TensorflowRecognitionResult result = classificator.prediction(image);
            result.sortPredictions(true);
            System.out.println(result.toString());
            System.out.println("Best result : " + result.getBestMatch());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
