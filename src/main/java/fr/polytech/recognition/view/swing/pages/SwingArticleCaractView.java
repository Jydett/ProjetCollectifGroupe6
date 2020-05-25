package fr.polytech.recognition.view.swing.pages;

import fr.polytech.recognition.controller.ArticleCaractController;
import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.view.ArticleCaractView;
import fr.polytech.recognition.view.swing.SwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SwingArticleCaractView extends SwingView implements ArticleCaractView {

    private final ArticleCaractController controller;


    public SwingArticleCaractView(ArticleCaractController controller) {
        this.controller = controller;
        initialize();
    }

    public void initialize() {
        CharacterPanel = this; //todo a refactor

        splitPaneInCharacter = new JSplitPane();
        imageCharacter = new JLabel();
        scrollPaneInCharacter = new JScrollPane();
        CharacterTable = new JTable();
//        btnSaveArticle = new JButton();
//        btnDeleteArticle = new JButton();
        //======== CharacterPanel ========
        {
            CharacterPanel.setLayout(null);

            //======== splitPane2 ========
            {

                //---- image3 ----
                imageCharacter.setText("Votre image apparaitra ici");
                imageCharacter.setBackground(new Color(204, 204, 204));
                imageCharacter.setForeground(new Color(148, 148, 148));
                imageCharacter.setFont(new Font("Avenir", Font.PLAIN, 16));
                imageCharacter.setEnabled(true);
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
            /*
            //---- btnSaveArticle ----
            btnSaveArticle.setText("Enregistrer l'article");
            btnSaveArticle.setFont(new Font("Avenir", Font.PLAIN, 16));
            btnSaveArticle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.btnSaveArticleActionPerformed(e);
                }
            });
            CharacterPanel.add(btnSaveArticle);
            btnSaveArticle.setBounds(420, 470, 230, 40);

            //---- btnDeleteArticle ----
            btnDeleteArticle.setText("Supprimer l'article");
            btnDeleteArticle.setFont(new Font("Avenir", Font.PLAIN, 16));
            btnDeleteArticle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.btnDeleteArticleActionPerformed(e, (Article) CharacterTable.getValueAt(CharacterTable.getSelectedRow(), 0));
                }
            });
            CharacterPanel.add(btnDeleteArticle);
            btnDeleteArticle.setBounds(680, 470, 230, 40);
             */
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

    @Override
    public String getTitle() {
        return "Caractéristique un article";
    }

    @Override
    public int priority() {
        return PageOrder.ARTICLE_CHARAC_ORDER;
    }

    private JPanel CharacterPanel;
    private JSplitPane splitPaneInCharacter;
    private JLabel imageCharacter;
    private JScrollPane scrollPaneInCharacter;
    private JTable CharacterTable;
 //   private JButton btnSaveArticle;
 //   private JButton btnDeleteArticle;
}
