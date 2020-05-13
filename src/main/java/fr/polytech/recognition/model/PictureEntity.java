package fr.polytech.recognition.model;

import fr.polytech.recognition.utils.ImageFormat;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import javax.persistence.Embeddable;
import java.awt.image.BufferedImage;
import java.io.*;

@Embeddable
@NoArgsConstructor
public class PictureEntity implements Serializable {
    private byte[] imageBytes;

    public PictureEntity(BufferedImage image, ImageFormat format) throws IOException {
        setImage(image, format);
    }

    public BufferedImage getImage() throws IOException {
        InputStream in = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(in);
    }

    public void setImage(BufferedImage image, ImageFormat format) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, format.getExtention(), out);
        imageBytes = out.toByteArray();
    }
}
