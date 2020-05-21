package fr.polytech.recognition.view.swing.pages;

import fr.polytech.recognition.controller.ImageChosenController;
import fr.polytech.recognition.view.ImageChosenView;
import fr.polytech.recognition.view.swing.SwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingImageChosenView extends SwingView implements ImageChosenView {

    private final ImageChosenController controller;

    private void btnNewImportActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

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
        btnNewImportInResult = new JButton();
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
    private JButton btnNewImportInResult;

    @Override
    public String getTitle() {//TODO i18n
        return "Choisir son image";
    }
}
