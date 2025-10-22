/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.radno_vreme;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.RadnoVreme;

/**
 *
 * @author kacan
 */
public class TableModelRadnoVreme extends AbstractTableModel{

   private final String[] kolone = {"Smena", "Datum"};
    private final List<RadnoVreme> radnaVremena;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public TableModelRadnoVreme(List<RadnoVreme> radnaVremena) {
        this.radnaVremena = radnaVremena;
    }

    public List<RadnoVreme> getRadnaVremena() {
        return radnaVremena;
    }

    @Override
    public int getRowCount() {
        return radnaVremena.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RadnoVreme rv = radnaVremena.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rv.getSmena().getNaziv();
            case 1:
                return rv.getDatum().format(formatter);
            default:
                throw new AssertionError("Nepostojeca kolona: " + columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
