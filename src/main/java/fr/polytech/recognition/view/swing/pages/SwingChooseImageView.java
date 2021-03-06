package fr.polytech.recognition.view.swing.pages;

import fr.polytech.recognition.controller.ChooseImageController;
import fr.polytech.recognition.view.ChooseImageView;
import fr.polytech.recognition.view.swing.SwingView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * La Swing vue de la tabbed panel Choose Image
 * Cette vue pour choisir une image et lancer la reconnaissance
 */
public class SwingChooseImageView extends SwingView implements ChooseImageView {
    private final ChooseImageController controller;
    private final JFileChooser jFileChooser = new JFileChooser();
    // filter of filename : jpg and jpeg
    private static final FileNameExtensionFilter IMG_FILTER = new FileNameExtensionFilter(
            "JPG & JPEG Images", "jpg", "jpeg");

    public SwingChooseImageView(ChooseImageController controller) {
        this.controller = controller;
        initialize();
    }

    /**
     * Quand le bouton d'importer une image est cliqué, ouvrir la fenetre du fichier
     * @param e Button clicked action event
     */
    private void btnImportActionPerformed(ActionEvent e) {
        controller.onImportButtonClicked(e);
    }

    /**
     * Quand le bouton de lancer la reconnaissance est cliqué, lancer la reconnaissance d'image
     * @param e Button clicked action event
     */
    private void btnLanceActionPerformed(ActionEvent e) {
        controller.onLanceButtonClicked(e);
    }

    public void initialize() {
        ImportPanel = this;
        btnImport = new JButton();
        imageLabel = new JLabel();
        btnLance = new JButton();

        //======== ImportPanel ========
        {
            ImportPanel.setLayout(null);

            //---- btnImport ----
            btnImport.setText("Importer image");
            btnImport.setFont(new Font("Avenir", Font.PLAIN, 16));
            btnImport.addActionListener(e -> btnImportActionPerformed(e));
            ImportPanel.add(btnImport);
            btnImport.setBounds(50, 35, 160, 30);

            //---- image ----
            imageLabel.setText(" Votre image apparaitra ici");
            imageLabel.setBackground(new Color(204, 204, 204));
            imageLabel.setForeground(new Color(148,148,148));
            imageLabel.setFont(new Font("Avenir", Font.PLAIN, 16));
            imageLabel.setEnabled(true);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-image.png")));
            ImportPanel.add(imageLabel);
            imageLabel.setBounds(200, 120, 525, 360);

            //---- btnLance ----
            btnLance.setText("Lancer la reconnaissance");
            btnLance.setFont(new Font("Avenir", Font.PLAIN, 16));
            /**
             * btnLanceAction
             */
            btnLance.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnLanceActionPerformed(e);
                }
            });
            ImportPanel.add(btnLance);
            btnLance.setBounds(625, 35, 265, 30);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < ImportPanel.getComponentCount(); i++) {
                    Rectangle bounds = ImportPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = ImportPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                ImportPanel.setMinimumSize(preferredSize);
                ImportPanel.setPreferredSize(preferredSize);
            }
        }
    }

    @Override
    public File chooseImage() {
        jFileChooser.setFileFilter(IMG_FILTER);
        int res = jFileChooser.showDialog(null, "Approve");
        if (res == JFileChooser.APPROVE_OPTION) {
            setImageLabel(jFileChooser.getSelectedFile());
            return jFileChooser.getSelectedFile();
        }
        return null;
    }

    private JPanel ImportPanel;
    private JButton btnImport;
    private JLabel imageLabel;
    private JButton btnLance;

    @Override
    public String getTitle() {
        return "     Choisir son image     ";
    }

    @Override
    public int priority() {
        return PageOrder.CHOOSE_IMAGE_ORDER;
    }

    @Override
    public Icon getIcon() {
        return super.getIcon();
    }

    /**
     * Affiche l'image sélectionnée sur la page ChooseImage
     * @param fileSelected Image sélectionnée
     */
    public void setImageLabel(File fileSelected){
        String path = fileSelected.getAbsolutePath();
        ImageIcon icon=new ImageIcon(path);

        // Scale the picture proportionally
        int imgWidth = icon.getIconWidth();
        int imgHeight = icon.getIconHeight();
        int conWidth = getWidth();
        int conHeight = getHeight();
        int reImgWidth;
        int reImgHeight;
        if(imgWidth / imgHeight >= conWidth / conHeight){
            if(imgWidth > conWidth){
                reImgWidth = conWidth;
                reImgHeight = imgHeight * reImgWidth / imgWidth;
            }else{
                reImgWidth = imgWidth;
                reImgHeight = imgHeight;
            }
        }else{
            if(imgWidth > conWidth){
                reImgHeight = conHeight;
                reImgWidth = imgWidth * reImgHeight / imgHeight;
            }else{
                reImgWidth = imgWidth;
                reImgHeight = imgHeight;
            }
        }
        // Scale the picture proportionally
        icon = new ImageIcon(icon.getImage().getScaledInstance(reImgWidth, reImgHeight, Image.SCALE_DEFAULT));

        // put the image into imageLabel
        imageLabel.setIcon(icon);
        imageLabel.setText("");
    }

}
