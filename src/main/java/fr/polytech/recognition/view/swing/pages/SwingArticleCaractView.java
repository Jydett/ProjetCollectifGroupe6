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
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * La Swing vue de la tabbed panel Article caractéristiques
 */
public class SwingArticleCaractView extends SwingView implements ArticleCaractView {

    private final ArticleCaractController controller;

    public SwingArticleCaractView(ArticleCaractController controller) {
        this.controller = controller;
        initialize();
    }

    public void initialize() {
        CharacterPanel = this;

        splitPaneInCharacter = new JSplitPane();
        imageCharacter = new JLabel();
        scrollPaneInCharacter = new JScrollPane();
        CharacterTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        CharacterTable.setModel(tableModel); //refactor the CharacterTable

//        btnSaveArticle = new JButton();
//        btnDeleteArticle = new JButton();
        //======== CharacterPanel ========
        {
            CharacterPanel.setLayout(null);

            //======== splitPane2 ========
            {

                //---- image3 ----
                imageCharacter.setText(" image");
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

    /**
     *  Ajoute les données de l'article selectionné dans le Jtab.
     * @param artSelected Article selectionné dans le Jtab d'ImageChosen
     */
    public void populateArticleTab(Article artSelected)
    {
        DefaultTableModel tableMod = (DefaultTableModel) CharacterTable.getModel();
        tableMod.setRowCount(0); // Clear the Table of all of his rows
        tableMod.setColumnCount(0);

        // java.util.List<Object> listHeader = new ArrayList<Object>( );
        tableMod.addColumn("Id Article");
        tableMod.addColumn("Nom de l'Article");
        tableMod.addColumn("Prix");
        tableMod.addColumn("Lien du Vendeur");
        //  tableMod.insertRow(0, listHeader.toArray());

        List<Object> listTable = new ArrayList<Object>();

        listTable.add(artSelected);
        listTable.add(artSelected.getName());
        listTable.add(artSelected.getPrice());
        listTable.add(artSelected.getVendorLink());

        tableMod.addRow(listTable.toArray());
    }

    @Override
    public String getTitle() {
        return "     Caractéristique de l'article     ";
    }

    @Override
    public int priority() {
        return PageOrder.ARTICLE_CHARAC_ORDER;
    }

    /**
     * Affiche l'image sélectionnée sur la page ChooseImage
     * @param fileSelected Image sélectionnée
     */
    public void setImageCharacter(File fileSelected){
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

        // put the image into ImageCharacter
        imageCharacter.setIcon(icon);
        imageCharacter.setText("");
    }

    private JPanel CharacterPanel;
    private JSplitPane splitPaneInCharacter;
    private JLabel imageCharacter;
    private JScrollPane scrollPaneInCharacter;
    private JTable CharacterTable;
 //   private JButton btnSaveArticle;
 //   private JButton btnDeleteArticle;
}
