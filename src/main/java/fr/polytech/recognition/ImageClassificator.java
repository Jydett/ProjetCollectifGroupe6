package fr.polytech.recognition;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class you will find the methods to exploit a tensorflow model
 */
public class ImageClassificator {
    private Session session;
    private Graph modelGraph;
    private List<String> labels;

    private int imageWidth;
    private int imageHeight;
    private float mean;
    private float scale;

    /**
     *
     * @param modelPathDef a path to your model pb file
     * @param labelsPathDef a path to your model list of labels
     * @param imageWidth specific to the model
     * @param imageHeigth specific to the model
     * @param mean specific to the model
     * @param scale speicifc to the model
     */
    public ImageClassificator(String modelPathDef, String labelsPathDef, int imageWidth, int imageHeigth, float mean, float scale) {
        this.imageHeight = imageHeigth;
        this.imageWidth = imageWidth;
        this.mean = mean;
        this.scale = scale;
        try {
            Path modelPath = Paths.get(this.getClass().getResource(modelPathDef).toURI());
            Path labelsPath = Paths.get(this.getClass().getResource(labelsPathDef).toURI());
            byte[] graphDef = Files.readAllBytes(modelPath);
            labels = Files.readAllLines(labelsPath);
            modelGraph = new Graph();
            modelGraph.importGraphDef(graphDef);
            session = new Session(modelGraph);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public  PredictionResult prediction(BufferedImage image) {
        // we only give one image to the model hence the size
        float [][][][] imageData = new float[1][imageHeight][imageWidth][3];
        imageData[0] = ImageConversion.imageToNormalizedRGBArray(image, imageHeight, imageWidth, mean, scale);

        Tensor imageTensor = Tensor.create(imageData, Float.class);
        Tensor result = session.runner().feed("input", imageTensor).fetch("output").run().get(0);

        return new PredictionResult(result, labels);
    }

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(ImageClassificator.class.getResource("/test_image.jpg"));
            ImageClassificator classificator = new ImageClassificator(
                    "/tensorflow_inception_graph.pb",
                    "/imagenet_comp_graph_label_strings.txt",
                    224,224, 117f, 1f
            );
            PredictionResult result = classificator.prediction(image);
            result.sortPredictions(true);
            System.out.println(result.toString());
            System.out.println("Best result : " + result.findBestLabel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
