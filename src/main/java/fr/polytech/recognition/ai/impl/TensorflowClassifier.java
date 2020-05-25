package fr.polytech.recognition.ai.impl;

import fr.polytech.recognition.ai.Classifier;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Image classification using a tensorflow model
 */
public class TensorflowClassifier implements Classifier<String> {
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
     * @param modelImageWidth Image width that is acceptable by the model
     * @param modelImageHeigth Image height that is acceptable by the model
     * @param modelMean RGB pixel value mean that is acceptable by the model (see ImageConversion class)
     * @param modelScale RGB pixel value scale that is acceptable by the model  (see ImageConversion class)
     */
    public TensorflowClassifier(String modelPathDef, String labelsPathDef, int modelImageWidth, int modelImageHeigth, float modelMean, float modelScale) {
        this.imageHeight = modelImageHeigth;
        this.imageWidth = modelImageWidth;
        this.mean = modelMean;
        this.scale = modelScale;
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

    @Override
    public TensorflowRecognitionResult prediction(BufferedImage image) {
        // we only give one image to the model hence the size
        float [][][][] imageData = new float[1][imageHeight][imageWidth][3];
        imageData[0] = ImageConversion.imageToNormalizedRGBArray(image, imageHeight, imageWidth, mean, scale);

        Tensor imageTensor = Tensor.create(imageData, Float.class);
        Tensor result = session.runner().feed("input", imageTensor).fetch("output").run().get(0);

        return new TensorflowRecognitionResult(result, labels);
    }



}
