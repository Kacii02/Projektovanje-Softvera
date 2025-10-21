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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kacan
 */
public class Iznajmljivanje implements Serializable, DomainObject<Iznajmljivanje> {

    private int idIznajmljivanje;
    private Bibliotekar bibliotekar;
    private Clan clan;
    private LocalDate datumIznajmljivanja;
    private LocalDate rokVracanja;
    private int brojStavkiIznajmljivanja;
    private Status status;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, Bibliotekar bibliotekar, Clan clan, LocalDate datumIznajmljivanja, LocalDate rokVracanja, int brojStavkiIznajmljivanja, Status status) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.bibliotekar = bibliotekar;
        this.clan = clan;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.rokVracanja = rokVracanja;
        this.brojStavkiIznajmljivanja = brojStavkiIznajmljivanja;
        this.status = status;
    }

    public Iznajmljivanje(Bibliotekar bibliotekar, Clan clan, LocalDate datumIznajmljivanja, LocalDate rokVracanja, int brojStavkiIznajmljivanja, Status status) {

        this.bibliotekar = bibliotekar;
        this.clan = clan;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.rokVracanja = rokVracanja;
        this.brojStavkiIznajmljivanja = brojStavkiIznajmljivanja;
        this.status = status;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public LocalDate getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public LocalDate getRokVracanja() {
        return rokVracanja;
    }

    public void setRokVracanja(LocalDate rokVracanja) {
        this.rokVracanja = rokVracanja;
    }

    public int getBrojStavkiIznajmljivanja() {
        return brojStavkiIznajmljivanja;
    }

    public void setBrojStavkiIznajmljivanja(int brojStavkiIznajmljivanja) {
        this.brojStavkiIznajmljivanja = brojStavkiIznajmljivanja;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", bibliotekar=" + bibliotekar + ", clan=" + clan + ", datumIznajmljivanja=" + datumIznajmljivanja + ", rokVracanja=" + rokVracanja + ", brojStavkiIznajmljivanja=" + brojStavkiIznajmljivanja + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        return this.idIznajmljivanje == other.idIznajmljivanje;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO iznajmljivanje (bibliotekar, clan, datumIznajmljivanja, rokVracanja, brojStavkiIznajmljivanja, status) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, bibliotekar.getIdBibliotekar());
        ps.setInt(2, clan.getIdClan());
        ps.setDate(3, Date.valueOf(datumIznajmljivanja));
        ps.setDate(4, Date.valueOf(rokVracanja));
        ps.setInt(5, brojStavkiIznajmljivanja);
        ps.setString(6, status.name());
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE iznajmljivanje "
                + "SET bibliotekar=?, clan=?, datumIznajmljivanja=?, rokVracanja=?, brojStavkiIznajmljivanja=?, status=? "
                + "WHERE idIznajmljivanje=?";

    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, bibliotekar.getIdBibliotekar());
        ps.setInt(2, clan.getIdClan());
        ps.setDate(3, Date.valueOf(datumIznajmljivanja));
        ps.setDate(4, Date.valueOf(rokVracanja));
        ps.setInt(5, brojStavkiIznajmljivanja);
        ps.setString(6, status.name());
        ps.setInt(7, idIznajmljivanje);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM iznajmljivanje "
                + "WHERE idIznajmljivanje=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idIznajmljivanje);
    }

    @Override
    public String getSelectQuery() {
        return """
           SELECT *
           FROM iznajmljivanje i
           JOIN bibliotekar b ON i.bibliotekar = b.idBibliotekar
           JOIN clan c ON i.clan = c.idClan
           LEFT JOIN tip_clanstva tc ON c.tipClanstva = tc.idTipClanstva;
           WHERE i.idIznajmljivanje=?
           """;
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idIznajmljivanje);
    }

    @Override
    public Iznajmljivanje createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            // Iznajmljivanje
            int rsIdIznajmljivanje = rs.getInt("i.idIznajmljivanje");
            LocalDate rsDatumIznajmljivanja = rs.getDate("i.datumIznajmljivanja").toLocalDate();
            LocalDate rsRokVracanja = rs.getDate("i.rokVracanja").toLocalDate();
            int rsBrojStavki = rs.getInt("i.brojStavkiIznajmljivanja");
            Status rsStatus = Status.valueOf(rs.getString("i.status"));

            // Bibliotekar
            int rsIdBibliotekar = rs.getInt("b.idBibliotekar");
            String rsImeB = rs.getString("b.ime");
            String rsPrezimeB = rs.getString("b.prezime");
            String rsEmailB = rs.getString("b.email");
            String rsLozinkaB = rs.getString("b.lozinka");

            Bibliotekar rsBibliotekar = new Bibliotekar(rsIdBibliotekar, rsImeB, rsPrezimeB, rsEmailB, rsLozinkaB);

            //Clan
            int rsIdClan = rs.getInt("c.idClan");
            String rsIme = rs.getString("c.ime");
            String rsPrezime = rs.getString("c.prezime");
            String rsEmail = rs.getString("c.email");
            String rsLozinka = rs.getString("c.lozinka");
            LocalDate rsDatumUclanjenja = rs.getDate("c.datumUclanjenja").toLocalDate();
            LocalDate rsDatumIstekaClanarine = rs.getDate("c.datumIstekaClanarine").toLocalDate();
            int rsTrajanjeClanarine = rs.getInt("c.trajanjeClanarine");
            int rsBrTrenutnihKnjiga = rs.getInt("c.brTrenutnihKnjiga");

            //Tip clanstva
            int rsIdTipClanstva = rs.getInt("tc.idTipClanstva");
            String rsNaziv = rs.getString("tc.naziv");
            int rsMaxIznajmljenihKnjiga = rs.getInt("tc.maxIznajmljenihKnjiga");

            TipClanstva tipclanstva = new TipClanstva(rsIdTipClanstva, rsNaziv, rsMaxIznajmljenihKnjiga);
            Clan rsClan = new Clan(rsIdClan, rsIme, rsPrezime, rsEmail, rsLozinka, rsDatumUclanjenja, rsDatumIstekaClanarine, rsTrajanjeClanarine, rsBrTrenutnihKnjiga, tipclanstva);

            return new Iznajmljivanje(rsIdIznajmljivanje, rsBibliotekar, rsClan, rsDatumIznajmljivanja, rsRokVracanja, rsBrojStavki, rsStatus);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return """
           SELECT *
           FROM iznajmljivanje i
           JOIN bibliotekar b ON i.bibliotekar = b.idBibliotekar
           JOIN clan c ON i.clan = c.idClan
           LEFT JOIN tip_clanstva tc ON c.tipClanstva = tc.idTipClanstva;
           """;
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<Iznajmljivanje> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Iznajmljivanje> lista = new ArrayList<>();
        while (rs.next()) {
            // Iznajmljivanje
            int rsIdIznajmljivanje = rs.getInt("i.idIznajmljivanje");
            LocalDate rsDatumIznajmljivanja = rs.getDate("i.datumIznajmljivanja").toLocalDate();
            LocalDate rsRokVracanja = rs.getDate("i.rokVracanja").toLocalDate();
            int rsBrojStavki = rs.getInt("i.brojStavkiIznajmljivanja");
            Status rsStatus = Status.valueOf(rs.getString("i.status"));

            // Bibliotekar
            int rsIdBibliotekar = rs.getInt("b.idBibliotekar");
            String rsImeB = rs.getString("b.ime");
            String rsPrezimeB = rs.getString("b.prezime");
            String rsEmailB = rs.getString("b.email");
            String rsLozinkaB = rs.getString("b.lozinka");

            Bibliotekar rsBibliotekar = new Bibliotekar(rsIdBibliotekar, rsImeB, rsPrezimeB, rsEmailB, rsLozinkaB);

            //Clan
            int rsIdClan = rs.getInt("c.idClan");
            String rsIme = rs.getString("c.ime");
            String rsPrezime = rs.getString("c.prezime");
            String rsEmail = rs.getString("c.email");
            String rsLozinka = rs.getString("c.lozinka");
            LocalDate rsDatumUclanjenja = rs.getDate("c.datumUclanjenja").toLocalDate();
            LocalDate rsDatumIstekaClanarine = rs.getDate("c.datumIstekaClanarine").toLocalDate();
            int rsTrajanjeClanarine = rs.getInt("c.trajanjeClanarine");
            int rsBrTrenutnihKnjiga = rs.getInt("c.brTrenutnihKnjiga");

            //Tip clanstva
            int rsIdTipClanstva = rs.getInt("tc.idTipClanstva");
            String rsNaziv = rs.getString("tc.naziv");
            int rsMaxIznajmljenihKnjiga = rs.getInt("tc.maxIznajmljenihKnjiga");

            TipClanstva tipclanstva = new TipClanstva(rsIdTipClanstva, rsNaziv, rsMaxIznajmljenihKnjiga);
            Clan rsClan = new Clan(rsIdClan, rsIme, rsPrezime, rsEmail, rsLozinka, rsDatumUclanjenja, rsDatumIstekaClanarine, rsTrajanjeClanarine, rsBrTrenutnihKnjiga, tipclanstva);

            lista.add(new Iznajmljivanje(rsIdIznajmljivanje, rsBibliotekar, rsClan, rsDatumIznajmljivanja, rsRokVracanja, rsBrojStavki, rsStatus));
        }
        return lista;
    }

}
