package fr.polytech.recognition.model.database;

import fr.polytech.recognition.utils.ImageFormat;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import java.awt.image.BufferedImage;
import java.io.*;

@Embeddable
@NoArgsConstructor
public class PictureEntity implements Serializable {

    @Lob
    private byte[] imageBytes;

    @Enumerated(EnumType.STRING)
    private ImageFormat format;


    public BufferedImage asBufferedImage() throws IOException {
        InputStream in = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(in);
    }

    public void setBufferedImage(BufferedImage image, ImageFormat format) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, format.getExtention(), out);
        imageBytes = out.toByteArray();
        this.format=format;
    }
}