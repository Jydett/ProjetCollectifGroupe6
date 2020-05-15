package fr.polytech.recognition.view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;

public class GUITest extends JFrame {

    public GUITest(){ initComponents(); }

    private void btnImportActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    private void btnLanceActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    private void btnNewImportActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    private void btnNewImport2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents(){
        FlatCyanLightIJTheme.install();

        TabbedPanel = new JTabbedPane();
        ImportPanel = new JPanel();
        btnImport = new JButton();
        imageLabel = new JLabel();
        btnLance = new JButton();
        ResultPanel = new JPanel();
        splitPaneInResult = new JSplitPane();
        imageChosen = new JLabel();
        scrollPaneInResult = new JScrollPane();
        ResultTable = new JTable();
        btnNewImportInResult = new JButton();
        CharacterPanel = new JPanel();
        splitPaneInCharacter = new JSplitPane();
        imageCharacter = new JLabel();
        scrollPaneInCharacter = new JScrollPane();
        CharacterTable = new JTable();
        btnNewImportInCharacter = new JButton();

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

            //======== ImportPanel ========
            {
                ImportPanel.setLayout(null);

                //---- btnImport ----
                btnImport.setText("Importer image");
                btnImport.setFont(new Font("Avenir", Font.PLAIN, 16));
                btnImport.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        btnImportActionPerformed(e);
                    }
                });
                ImportPanel.add(btnImport);
                btnImport.setBounds(50, 35, 160, 30);

                //---- image ----
                imageLabel.setText("Votre image apparaitra ici");
                imageLabel.setBackground(new Color(204, 204, 204));
                imageLabel.setFont(new Font("Avenir", Font.PLAIN, 13));
                imageLabel.setEnabled(false);
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imageLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-image.png")));
                ImportPanel.add(imageLabel);
                imageLabel.setBounds(200, 120, 525, 360);

                //---- btnLance ----
                btnLance.setText("Lancer la reconnaissance");
                btnLance.setFont(new Font("Avenir", Font.PLAIN, 16));
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
                    for(int i = 0; i < ImportPanel.getComponentCount(); i++) {
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
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/icon-image-s.png"));
            TabbedPanel.addTab("  Importer image      ",icon,ImportPanel);

            //======== ResultPanel ========
            {
                ResultPanel.setLayout(null);

                //======== splitPane1 ========
                {

                    //---- image2 ----
                    imageChosen.setText("Votre image apparaitra ici");
                    imageChosen.setBackground(new Color(204, 204, 204));
                    imageChosen.setFont(new Font("Avenir", Font.PLAIN, 13));
                    imageChosen.setEnabled(false);
                    imageChosen.setHorizontalAlignment(SwingConstants.CENTER);
                    imageChosen.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-image.png")));
                    splitPaneInResult.setLeftComponent(imageChosen);

                    //======== scrollPane ========
                    {
                        scrollPaneInResult.setViewportView(ResultTable);
                    }
                    splitPaneInResult.setRightComponent(scrollPaneInResult);
                }
                ResultPanel.add(splitPaneInResult);
                splitPaneInResult.setBounds(0, 0, 915, 470);
                splitPaneInResult.setDividerLocation(400);

                //---- btnNewImport ----
                btnNewImportInResult.setText("Importer nouvelle image");
                btnNewImportInResult.setFont(new Font("Avenir", Font.PLAIN, 16));
                btnNewImportInResult.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        btnNewImportActionPerformed(e);
                    }
                });
                ResultPanel.add(btnNewImportInResult);
                btnNewImportInResult.setBounds(50, 470, 290, 40);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < ResultPanel.getComponentCount(); i++) {
                        Rectangle bounds = ResultPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = ResultPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    ResultPanel.setMinimumSize(preferredSize);
                    ResultPanel.setPreferredSize(preferredSize);
                }
            }
            TabbedPanel.addTab("  R\u00e9sultat de la reconnaissance      ", ResultPanel);

            //======== panel1 ========
            {
                CharacterPanel.setLayout(null);

                //======== splitPane2 ========
                {

                    //---- image3 ----
                    imageCharacter.setText("Votre image apparaitra ici");
                    imageCharacter.setBackground(new Color(204, 204, 204));
                    imageCharacter.setFont(new Font("Avenir", Font.PLAIN, 13));
                    imageCharacter.setEnabled(false);
                    imageCharacter.setHorizontalAlignment(SwingConstants.CENTER);
                    imageCharacter.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-image.png")));
                    splitPaneInCharacter.setLeftComponent(imageCharacter);

                    //======== scrollPane2 ========
                    {
                        scrollPaneInCharacter.setViewportView(CharacterTable);
                    }
                    splitPaneInCharacter.setRightComponent(scrollPaneInCharacter);
                }
                CharacterPanel.add(splitPaneInCharacter);
                splitPaneInCharacter.setBounds(0, 0, 915, 470);
                splitPaneInCharacter.setDividerLocation(400);

                //---- btnNewImport2 ----
                btnNewImportInCharacter.setText("Importer nouvelle image");
                btnNewImportInCharacter.setFont(new Font("Avenir", Font.PLAIN, 16));
                btnNewImportInCharacter.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        btnNewImport2ActionPerformed(e);
                    }
                });
                CharacterPanel.add(btnNewImportInCharacter);
                btnNewImportInCharacter.setBounds(50, 470, 290, 40);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < CharacterPanel.getComponentCount(); i++) {
                        Rectangle bounds = CharacterPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = CharacterPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    CharacterPanel.setMinimumSize(preferredSize);
                    CharacterPanel.setPreferredSize(preferredSize);
                }
            }
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
    private JButton btnImport;
    private JLabel imageLabel;
    private JButton btnLance;
    private JPanel ResultPanel;
    private JSplitPane splitPaneInResult;
    private JLabel imageChosen;
    private JScrollPane scrollPaneInResult;
    private JTable ResultTable;
    private JButton btnNewImportInResult;
    private JPanel CharacterPanel;
    private JSplitPane splitPaneInCharacter;
    private JLabel imageCharacter;
    private JScrollPane scrollPaneInCharacter;
    private JTable CharacterTable;
    private JButton btnNewImportInCharacter;
}
