package fr.polytech.recognition.view.swing.pages;

import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.event.events.ArticleClickedEvent;
import fr.polytech.recognition.event.events.ImageChoosenEvent;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.view.ImageChosenView;
import fr.polytech.recognition.view.swing.SwingView;
import fr.polytech.recognition.view.swing.model.ArticleTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwingImageChosenView extends SwingView implements ImageChosenView {

    private final ImageChosenController controller;


    public SwingImageChosenView(ImageChosenController controller) {
        this.controller = controller;
        initialize();
    }

    public void initialize() {
        ResultPanel = this; //TOdo a refactor
        splitPaneInResult = new JSplitPane();
        imageChosen = new JLabel();
        scrollPaneInResult = new JScrollPane();
        ResultTable = new JTable();
        //======== ResultPanel ========
        {
            ResultPanel.setLayout(null);

            //======== splitPane1 ========
            {

                //---- image2 ----
                imageChosen.setText(" image");
                imageChosen.setBackground(new Color(204, 204, 204));
                imageChosen.setForeground(new Color(148,148,148));
                imageChosen.setFont(new Font("Avenir", Font.PLAIN, 16));
                imageChosen.setEnabled(true);
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

            ResultTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(ResultTable.getSelectedRow() != -1)
                    {
                        controller.getRooter().dispatchEvent(new ArticleClickedEvent((Article) ResultTable.getValueAt(ResultTable.getSelectedRow(),0)));
                    }
                }
            });

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < ResultPanel.getComponentCount(); i++) {
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

    }
    private JPanel ResultPanel;
    private JSplitPane splitPaneInResult;
    private JLabel imageChosen;
    private JScrollPane scrollPaneInResult;
    private JTable ResultTable;

    public void afficherArticleList(Map<Article, Float> res){
        DefaultTableModel tableMod = (DefaultTableModel) ResultTable.getModel();
        tableMod.setRowCount(0); // Clear the Table of all of his rows
        tableMod.setColumnCount(0);

       // java.util.List<Object> listHeader = new ArrayList<Object>( );
        tableMod.addColumn("Id Article");
        tableMod.addColumn("Nom de l'Article");
        tableMod.addColumn("Probabilité");
      //  tableMod.insertRow(0, listHeader.toArray());

        System.out.println("SIZE RES ============== " + res.entrySet().size());

        for(Map.Entry<Article, Float> entry : res.entrySet())
        {
            List<Object> listTable = new ArrayList<Object>();
            listTable.add(entry.getKey());
            listTable.add(entry.getKey().getName()); // Article
            listTable.add(entry.getValue()); // Float

            tableMod.addRow(listTable.toArray());
        }

    }



    @Override
    public String getTitle() {//TODO i18n
        return "    Analyser l'image    ";
    }

    @Override
    public int priority() {
        return PageOrder.IMAGE_CHOOSEN_ORDER;
    }

    @Override
    public Icon getIcon() {
        return super.getIcon();
    }

    /**
     * Affiche l'image sélectionnée sur la page ChooseImage
     * @param fileSelected Image sélectionnée
     */
    public void setImageChosen(File fileSelected){
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

        // put the image into ImageChosen
        imageChosen.setIcon(icon);
        imageChosen.setText("");
    }
}
