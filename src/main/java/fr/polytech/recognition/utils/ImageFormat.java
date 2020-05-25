package fr.polytech.recognition.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum  ImageFormat {
    PNG("PNG"),
    JPG("JPG");
    @Getter
    private final String extention;
}
