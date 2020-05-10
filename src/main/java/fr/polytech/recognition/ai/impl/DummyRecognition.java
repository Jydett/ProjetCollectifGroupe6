package fr.polytech.recognition.ai.impl;

import fr.polytech.recognition.ai.Recognition;
import fr.polytech.recognition.ai.RecognitionResults;

import java.awt.image.BufferedImage;

public class DummyRecognition implements Recognition {
    @Override
    public RecognitionResults recognize(BufferedImage image) {
        return new RecognitionResults();
    }
}
