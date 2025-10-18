/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.knjiga;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;

/**
 *
 * @author kacan
 */
public class TableModelKnjiga extends AbstractTableModel{
    private String [] columns = {"Naslov","ISBN","Autor","Izdavac"};
    private List<Knjiga> knjige;
    
    TableModelKnjiga(List<Knjiga> knjige){
        this.knjige = knjige;
    }
 
    @Override
    public int getRowCount() {
        return knjige.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga k = knjige.get(rowIndex);
        switch (columnIndex) {
            case 0:return k.getNaslov();
            case 1: return k.getIsbn();
            case 2: return k.getAutor();
            case 3: return k.getIzdavac();
            default:
                throw new AssertionError();
        }
    
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    Knjiga getKnjiga(int izbor) {
        return knjige.get(izbor);
    }
    
    
}
