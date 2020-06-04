package fr.polytech.recognition.view.swing;

import fr.polytech.recognition.view.swing.model.SingleSelectionWithConfirmationModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * La Swing vue de la whole tabbed frame
 */
public class TabbedFrame extends JFrame {

    private final SingleSelectionWithConfirmationModel selectionModel;

    /**
     * Decider l'appearance de Swing frame: la taille, le front, le titre, les panels , etc
     * @param context SwingViewTabbedContext
     */
    public TabbedFrame(SwingViewTabbedContext context) {
        tabbedPanel = new JTabbedPane();
        selectionModel = new SingleSelectionWithConfirmationModel() {
            @Override
            public boolean confirm(int index) {
                return context.switchView(index);
            }
        };
        tabbedPanel.setModel(selectionModel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFont(new Font("Avenir", Font.PLAIN, 13));
        setName("Application");
        tabbedPanel.setFont(new Font("Avenir", Font.PLAIN, 16));
        tabbedPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(tabbedPanel, BorderLayout.CENTER);
        tabbedPanel.setBounds(0, 0, 920, 580);
    }

    /**
     * Decider la taille de la frame
     */
    @Override
    public void pack() {
        Dimension preferredSize = new Dimension();
        Container contentPane = getContentPane();
        for (int i = 0; i < contentPane.getComponentCount(); i++) {
            Rectangle bounds = contentPane.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        Insets insets = contentPane.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        contentPane.setMinimumSize(new Dimension(950, 600));
        contentPane.setPreferredSize(new Dimension(950, 600));
        super.pack();
        setLocationRelativeTo(getOwner());
    }

    @Getter
    private JTabbedPane tabbedPanel;

    public void switchView(int index) {
        selectionModel.forceSetSelectedIndex(index);
    }
}
