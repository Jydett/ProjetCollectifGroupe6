package fr.polytech.recognition.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum  ImageFormat {
    PNG("PNG");
    @Getter
    private final String extention;
}
