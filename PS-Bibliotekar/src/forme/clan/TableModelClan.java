/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.clan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Clan;

/**
 *
 * @author kacan
 */
public class TableModelClan extends AbstractTableModel {

    private String[] columns = {"Ime", "Prezime", "Email", "Datum uclanjenja", "Datum isteka clanarine", "Trajanje clanarine", "Broj pozajmljenih knjiga", "Tip clanstva"};
    private List<Clan> clanovi;

    TableModelClan(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }

    @Override
    public int getRowCount() {
        return clanovi.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Clan clan = clanovi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return clan.getIme();
            case 1:
                return clan.getPrezime();
            case 2:
                return clan.getEmail();
            case 3:
                return clan.getDatumUclanjenja().format(formatter);
            case 4:
                return clan.getDatumIstekaClanarine().format(formatter);
            case 5:
                return clan.getTrajanjeClanarine();
            case 6:
                return clan.getBrTrenutnihKnjiga();
            case 7:
                return clan.getTipClanstva().toString();
            default:
                throw new AssertionError("Nepoznata kolona");
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Clan getClan(int izbor) {
        return clanovi.get(izbor);
    }

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//
//        switch (columnIndex) {
//            case 0: // "Ime"
//            case 1: // "Prezime"
//            case 2: // "Email"
//                return String.class; // Sve ove kolone sadrže stringove
//            case 3: // "Datum uclanjenja"
//            case 4: // "Datum isteka clanarine"
//                return LocalDate.class; // Pretpostavljamo da je datum tipa java.util.Date
//            case 5: // "Trajanje clanarine"
//                return Integer.class; // Pretpostavljamo da je trajanje u mesecima, pa je Integer
//            case 6: // "Broj pozajmljenih knjiga"
//                return Integer.class; // Pretpostavljamo da je ovo broj (Integer)
//            case 7: // "Tip clanstva"
//                return String.class; // Ovo bi mogao biti tip stringa (npr. "Standard", "Premium", itd.)
//            default:
//                throw new IllegalArgumentException("Nepoznata kolona");
//        }
//    }
}


