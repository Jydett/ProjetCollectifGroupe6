package fr.polytech.recognition.view.swing.model;

import javax.swing.*;

public abstract class SingleSelectionWithConfirmationModel extends DefaultSingleSelectionModel {

    @Override
    public void setSelectedIndex(int newIndex) {
        int currentIndex = getSelectedIndex();
        if (currentIndex == -1) {
            super.setSelectedIndex(newIndex);
        } else {
            if (confirm(newIndex)) {
                super.setSelectedIndex(newIndex);
            }
        }
    }

    public void forceSetSelectedIndex(int newIndex) {
        super.setSelectedIndex(newIndex);
    }

    protected abstract boolean confirm(int newIndex);
}
