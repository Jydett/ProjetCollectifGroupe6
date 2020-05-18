package fr.polytech.recognition.view.swing;

import fr.polytech.recognition.controller.ArticleCaractController;
import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.view.ArticleCaractView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingArticleCaractView extends SwingView implements ArticleCaractView {

    private final ArticleCaractController controller;

    private void btnNewImport2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    public SwingArticleCaractView(FrameContainer container, ArticleCaractController controller) {
        super(container.getFrame());

        this.controller = controller;
    }

    protected void initialize() {
        CharacterPanel = new JPanel();

        splitPaneInCharacter = new JSplitPane();
        imageCharacter = new JLabel();
        scrollPaneInCharacter = new JScrollPane();
        CharacterTable = new JTable();
        btnNewImportInCharacter = new JButton();

        //======== CharacterPanel ========
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
                for (int i = 0; i < CharacterPanel.getComponentCount(); i++) {
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

    }
    private JPanel CharacterPanel;
    private JSplitPane splitPaneInCharacter;
    private JLabel imageCharacter;
    private JScrollPane scrollPaneInCharacter;
    private JTable CharacterTable;
    private JButton btnNewImportInCharacter;
}
