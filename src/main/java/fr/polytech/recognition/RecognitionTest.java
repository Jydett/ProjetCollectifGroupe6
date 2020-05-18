package fr.polytech.recognition;

import fr.polytech.recognition.view.swing.MainWindowsView;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RecognitionTest extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new MainWindowsView();
            jFrame.setVisible(true);
        });
    }

    private static final String MODELPATH;
    private static final List<String> LABELS;
    private static byte[] GRAPH_DEF;
    private static final Graph GRAPH;
    private final JLabel img = new JLabel();
    private final JFileChooser jFileChooser = new JFileChooser();

    static {
        System.out.println("loading model..");
        MODELPATH = "./src/main/resources";
        byte[] GRAPH_DEF = readAllBytesOrExit(Paths.get(MODELPATH, "tensorflow_inception_graph.pb"));
        GRAPH = new Graph();
        GRAPH.importGraphDef(GRAPH_DEF);
        LABELS = readAllLinesOrExit(Paths.get(MODELPATH, "imagenet_comp_graph_label_strings.txt"));
        System.out.println("model loaded");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static final FileNameExtensionFilter IMG_FILTER = new FileNameExtensionFilter(
            "JPG & JPEG Images", "jpg", "jpeg");

    public RecognitionTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setSize(300, 300);
        JButton importer_une_image = new JButton("Importer une image");
        getContentPane().add(importer_une_image, BorderLayout.CENTER);
        importer_une_image.addActionListener(a -> {
            jFileChooser.setAccessory(img);
            // When any JFileChooser property changes, this handler
            // is executed
            jFileChooser.addPropertyChangeListener(pe -> {
                // Create SwingWorker for smooth experience
                SwingWorker<Image,Void> worker= new SwingWorker<Image,Void>(){
                    // The image processing method
                    protected Image doInBackground() {
                        // If selected file changes..
                        if (pe.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
                            // Get selected file
                            File f = jFileChooser.getSelectedFile();
                            try {
                                // Create FileInputStream for file
                                FileInputStream fin = new FileInputStream(f);

                                // Read image from fin
                                BufferedImage bim = ImageIO.read(fin);

                                // Return the scaled version of image
                                return bim.getScaledInstance(178,170,BufferedImage.SCALE_FAST);

                            } catch(Exception e) {
                                // If there is a problem reading image,
                                // it might not be a valid image or unable
                                // to read
                                img.setIcon(null);
                                img.setText(" Not valid image/Unable to read");
                            }
                        }

                        return null;
                    }

                    protected void done()
                    {
                        try
                        {
                            // Get the image
                            Image i=get(1L, TimeUnit.NANOSECONDS);

                            // If i is null, go back!
                            if(i==null) return;

                            // Set icon otherwise
                            img.setIcon(new ImageIcon(i));
                            img.setText(null);
                        }catch(Exception e){
                            // Print error occured
                            img.setIcon(null);
                            img.setText(" Error occured.");
                        }
                    }
                };

                // Start worker thread
                worker.execute();
            });
            jFileChooser.setFileFilter(IMG_FILTER);
            int res = jFileChooser.showDialog(null, "Approve");
            if (res == JFileChooser.APPROVE_OPTION) {
                sendRequest(jFileChooser.getSelectedFile());
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                GRAPH.close();
            }
        });
        pack();
    }
    private void sendRequest(File selectedFile) {
        try (Tensor imgToGess = Tensor.create(readAllBytesOrExit(selectedFile.toPath()))) {
            final float[] gessProbability = getGessProbability(imgToGess);
            int bestLabelIdx = maxIndex(gessProbability);
            JOptionPane.showMessageDialog(null, String.format(
                    "BEST MATCH: %s (%.2f%% likely)",
                    LABELS.get(bestLabelIdx), gessProbability[bestLabelIdx] * 100f));
        }
    }
    private static int maxIndex(float[] probabilities) {
        int best = 0;
        for (int i = 1; i < probabilities.length; ++i) {
            if (probabilities[i] > probabilities[best]) {
                best = i;
            }
        }
        return best;
    }

    private float[] getGessProbability(Tensor image) {
        try(Session s = new Session(GRAPH);
                Tensor result = s.runner()
                    .feed("DecodeJpeg/contents", image)
                    .fetch("softmax").run().get(0)) {
            final long[] rshape = result.shape();
            if (result.numDimensions() != 2 || rshape[0] != 1) {
                throw new RuntimeException(
                    String.format(
                        "Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
                        Arrays.toString(rshape)));
            }
            return result.copyTo(new float[1][(int) rshape[1]])[0];
        }
    }

    private static byte[] readAllBytesOrExit(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static List<String> readAllLinesOrExit(Path path) {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
