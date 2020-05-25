package fr.polytech.recognition;

import java.awt.image.BufferedImage;

/**
 * Interface for different predictions
 */
public interface Classifier {
    /**
     * Classify an image
     * @param image The image to classift
     * @return A prediction result consisting of labels and the estimated probability that they are correct
     */
    PredictionResult prediction(BufferedImage image);
}
