package fr.polytech.recognition.view;

import fr.polytech.recognition.model.database.Article;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface ArticleCaractView extends View {

    public void populateArticleTab(Article artSelected);
    public void setImageCharacter(File fileSelected);
}
