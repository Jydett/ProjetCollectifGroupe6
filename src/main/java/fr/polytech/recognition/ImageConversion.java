package fr.polytech.recognition;

import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.opencv_core.cvGet2D;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import java.awt.image.BufferedImage;

/**
 * Useful image convertion methods to make an image comply with a model standard
 */
public class ImageConversion {
    /**
     * Converts and normalize the given image so that it can be exploited by a tensorflow model
     * @param image The image to convert
     * @param targetHeight Specified by the model
     * @param targetWidth Specified by the model
     * @param mean Specified by the model
     * @param scale Specified by the model
     * @return the array [height][width][3] (every pixel and associated RGBs)
     */
    public static float[][][] imageToNormalizedRGBArray(BufferedImage image, int targetHeight, int targetWidth, float mean, float scale) {
        float[][][] result = new float[targetHeight][targetWidth][3];
        IplImage convertedImage = convertImage(image);
        IplImage resizedImage = resizeImage(targetWidth, targetHeight, convertedImage);
        for (int pixy = 0; pixy <  resizedImage.height() ; pixy++) {
            for (int pixx = 0 ; pixx < resizedImage.width() ; pixx++) {
                CvScalar pixel = cvGet2D(resizedImage, pixy, pixx);
                result[pixy][pixx][0] = (float)(pixel.val(2) - mean) / scale; //R
                result[pixy][pixx][1] = (float)(pixel.val(1) - mean) / scale; //G
                result[pixy][pixx][2] = (float)(pixel.val(0) - mean) / scale; //B
            }
        }
        return result;
    }
    /**
     * Convert a buffered image to IplImage
     * @param image the image to convert
     * @return the newly created IplImage
     */
    protected static IplImage convertImage(BufferedImage image) {
        ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
        IplImage iplImage = iplConverter.convert(java2dConverter.convert(image));
        return iplImage;
    }
    /**
     * Resizes the given image
     * @param targetHeight
     * @param targetWidth
     * @param image
     * @return
     */
    protected static IplImage resizeImage(int targetHeight, int targetWidth, IplImage image) {
        IplImage resizedImage = IplImage.create(targetWidth, targetHeight, image.depth(), image.nChannels());
        cvResize(image, resizedImage);
        return resizedImage;
    }
}
