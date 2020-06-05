package fr.polytech.recognition.view.swing.model;

import fr.polytech.recognition.model.database.Article;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArticleTableModel extends AbstractTableModel {

    public static final int CONTENT_COLUMN = 1;
    private LinkedHashMap<Article, Float> resMap = new LinkedHashMap<>();

    private static final String[] HEADERS = {"Article", "Probabilité"};

    @Override
    public String getColumnName(int columnIndex) {
        return HEADERS[columnIndex];
    }

    @Override
    public int getRowCount() {
        return resMap.size();
    }

    @Override
    public int getColumnCount() {
        return HEADERS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void addAll(LinkedHashMap<Article, Float> resMap){
        if(resMap != null){
            this.resMap = resMap;
            fireTableDataChanged();
        }
    }

    public Article getSelectedArticle(int rowSelected)
    {
        Article artSelected = (Article) new ArrayList<>(resMap.keySet()).get(rowSelected);
        return artSelected;
    }
}
