package fr.polytech.recognition.view.swing.model;

import fr.polytech.recognition.model.database.Article;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

public class ArticleTableModel extends AbstractTableModel {

    public static final int CONTENT_COLUMN = 1;
    private Map<Article, Float> resMap = new HashMap<>();

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

    public void addAll(Map<Article, Float> resMap){
        if(resMap != null){
            this.resMap = resMap;
            fireTableDataChanged();
        }
    }
}
