/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.DomainObject;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kacan
 */
public class RadnoVreme implements Serializable, DomainObject<RadnoVreme> {

    private Bibliotekar bibliotekar;
    private Smena smena;
    private LocalDate datum;

    public RadnoVreme() {
    }

    public RadnoVreme(Bibliotekar bibliotekar, Smena smena, LocalDate datum) {
        this.bibliotekar = bibliotekar;
        this.smena = smena;
        this.datum = datum;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "RadnoVreme{" + "bibliotekar= " + bibliotekar + ", smena= " + smena + ", datum= " + datum + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RadnoVreme other = (RadnoVreme) obj;
        if (!Objects.equals(this.bibliotekar, other.bibliotekar)) {
            return false;
        }
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO radno_vreme (bibliotekar, smena, datum) VALUES (?,?,?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, bibliotekar.getIdBibliotekar());
        ps.setInt(2, smena.getIdSmena());
        ps.setDate(3, Date.valueOf(datum));
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE radno_vreme SET smena=? "
                + "WHERE bibliotekar=? AND datum=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, smena.getIdSmena());
        ps.setInt(2, bibliotekar.getIdBibliotekar());
        ps.setDate(3, Date.valueOf(datum));
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM radno_vreme "
                + "WHERE bibliotekar=? AND smena=? AND datum=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, bibliotekar.getIdBibliotekar());
        ps.setInt(2, smena.getIdSmena());
        ps.setDate(3, Date.valueOf(datum));
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * "
                + "FROM radno_vreme r JOIN smena s ON r.smena=s.idSmena JOIN bibliotekar b ON r.bibliotekar=b.idBibliotekar "
                + "WHERE s.bibliotekar=? AND s.datum=?";

    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, bibliotekar.getIdBibliotekar());
        ps.setDate(2, Date.valueOf(datum));
    }

    @Override
    public RadnoVreme createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            //Bibliotekar
            int rsIdBibliotekar = rs.getInt("idBibliotekar");
            String rsIme = rs.getString("ime");
            String rsPrezime = rs.getString("prezime");
            String rsEmail = rs.getString("email");
            String rsLozinka = rs.getString("lozinka");
            Bibliotekar rsBibliotekar = new Bibliotekar(rsIdBibliotekar, rsIme, rsPrezime, rsEmail, rsLozinka);

            //Smena
            int rsIdSmena = rs.getInt("idSmena");
            String rsNaziv = rs.getString("naziv");
            LocalTime rsPocetak = rs.getTime("pocetak").toLocalTime();
            LocalTime rsKraj = rs.getTime("kraj").toLocalTime();
            Smena rsSmena = new Smena(rsIdSmena, rsNaziv, rsPocetak, rsKraj);

            //Datum
            LocalDate rsDatum = rs.getDate("r.datum").toLocalDate();

            return new RadnoVreme(rsBibliotekar, rsSmena, rsDatum);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * "
                + "FROM radno_vreme r JOIN smena s ON r.smena=s.idSmena JOIN bibliotekar b ON r.bibliotekar=b.idBibliotekar";

    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<RadnoVreme> createListFromResultSet(ResultSet rs) throws SQLException {
       List<RadnoVreme> lista = new ArrayList<>();
        while (rs.next()) {
            //Bibliotekar
            int rsIdBibliotekar = rs.getInt("idBibliotekar");
            String rsIme = rs.getString("ime");
            String rsPrezime = rs.getString("prezime");
            String rsEmail = rs.getString("email");
            String rsLozinka = rs.getString("lozinka");
            Bibliotekar rsBibliotekar = new Bibliotekar(rsIdBibliotekar, rsIme, rsPrezime, rsEmail, rsLozinka);

            //Smena
            int rsIdSmena = rs.getInt("idSmena");
            String rsNaziv = rs.getString("naziv");
            LocalTime rsPocetak = rs.getTime("pocetak").toLocalTime();
            LocalTime rsKraj = rs.getTime("kraj").toLocalTime();
            Smena rsSmena = new Smena(rsIdSmena, rsNaziv, rsPocetak, rsKraj);

            //Datum
            LocalDate rsDatum = rs.getDate("r.datum").toLocalDate();

            lista.add(new RadnoVreme(rsBibliotekar, rsSmena, rsDatum));
        }
        return lista;
    
    }

}
