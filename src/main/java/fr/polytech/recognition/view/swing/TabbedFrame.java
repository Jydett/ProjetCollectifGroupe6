package fr.polytech.recognition.view.swing;

import fr.polytech.recognition.view.swing.model.SingleSelectionWithConfirmationModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class TabbedFrame extends JFrame {

    private final SingleSelectionWithConfirmationModel selectionModel;

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
        contentPane.setMinimumSize(preferredSize);
        contentPane.setPreferredSize(preferredSize);
        super.pack();
        setLocationRelativeTo(getOwner());
    }

    @Getter
    private JTabbedPane tabbedPanel;

    public void switchView(int index) {
        selectionModel.forceSetSelectedIndex(index);
    }
}
