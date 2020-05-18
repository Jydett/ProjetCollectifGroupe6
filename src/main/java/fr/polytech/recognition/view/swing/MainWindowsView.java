package fr.polytech.recognition.view.swing;

import javax.swing.*;
import java.awt.*;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;

public class MainWindowsView extends JFrame {

    public MainWindowsView(){ initComponents(); }


    private void initComponents(){
        FlatCyanLightIJTheme.install();

        TabbedPanel = new JTabbedPane();
        ImportPanel = new SwingChooseImageView();
        ResultPanel = new SwingImageChosenView();
        CharacterPanel = new SwingArticleCaractView();
        //======== this ========
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFont(new Font("Avenir", Font.PLAIN, 13));
        setName("Application");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== TabbedPanel ========
        {
            TabbedPanel.setFont(new Font("Avenir", Font.PLAIN, 16));
            TabbedPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


            ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/icon-image-s.png"));
            TabbedPanel.addTab("  Importer image      ",icon,ImportPanel);

            TabbedPanel.addTab("  R\u00e9sultat de la reconnaissance      ", ResultPanel);

            TabbedPanel.addTab("      Caract\u00e9ristique de l\u2019article      ", CharacterPanel);
        }
        contentPane.add(TabbedPanel,"Center");
        TabbedPanel.setBounds(0, 0, 920, 580);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());


    }

    private JTabbedPane TabbedPanel;
    private JPanel ImportPanel;
    private JPanel ResultPanel;
    private JPanel CharacterPanel;
}
