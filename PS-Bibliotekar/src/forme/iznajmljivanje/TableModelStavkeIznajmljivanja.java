/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.iznajmljivanje;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaIznajmljivanja;

/**
 *
 * @author kacan
 */
public class TableModelStavkeIznajmljivanja extends AbstractTableModel {

    private String[] columns = {"Knjiga", "Napomena"};
    private List<StavkaIznajmljivanja> stavke;

    public TableModelStavkeIznajmljivanja(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        return stavke == null ? 0 : stavke.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja s = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getKnjiga().toString();
            case 1:
                return s.getNapomena();
            default:
                return null;
        }
    }

    public StavkaIznajmljivanja getStavka(int rowIndex) {
        return stavke.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
     public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

}
