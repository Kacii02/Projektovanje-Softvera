/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Bibliotekar;
import model.Clan;
import model.Iznajmljivanje;

/**
 *
 * @author kacan
 */
public class TableModelIznajmljivanje extends AbstractTableModel {

    private String[] columns = {"Bibliotekar", "Datum iznajmljivanja", "Rok vracanja", "Broj stavki iznajmljivanja", "Status"};
    private List<Iznajmljivanje> iznajmljivanja;

    public TableModelIznajmljivanje(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
    }

    @Override
    public int getRowCount() {
        return iznajmljivanja.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje iznajmljivanje = iznajmljivanja.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                Bibliotekar bibliotekar = iznajmljivanje.getBibliotekar();
                return bibliotekar != null ? bibliotekar.getIme() + " " + bibliotekar.getPrezime() : "Nepoznat";
            case 1: 
                return iznajmljivanje.getDatumIznajmljivanja();
            case 2: 
                return iznajmljivanje.getRokVracanja();
            case 3:
                return iznajmljivanje.getBrojStavkiIznajmljivanja();
            case 4: 
                return iznajmljivanje.getStatus();
            default:
                throw new AssertionError("Nepoznata kolona");
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    Iznajmljivanje getIznajmljivanje(int redIznajmljivanje) {
       return iznajmljivanja.get(redIznajmljivanje);
    }

}
