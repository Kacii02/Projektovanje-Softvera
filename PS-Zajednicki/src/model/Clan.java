/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.DomainObject;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kacan
 */
public class Clan implements Serializable, DomainObject<Clan> {

    private int idClan;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private LocalDate datumUclanjenja;
    private LocalDate datumIstekaClanarine;
    private int trajanjeClanarine;
    private int brTrenutnihKnjiga;
    private TipClanstva tipClanstva;

    public Clan() {
    }

    public Clan(int idClan, String ime, String prezime, String email, String lozinka, LocalDate datumUclanjenja, LocalDate datumIstekaClanarine, int trajanjeClanarine, int brTrenutnihKnjiga, TipClanstva tipClanstva) {
        this.idClan = idClan;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.datumUclanjenja = datumUclanjenja;
        this.datumIstekaClanarine = datumIstekaClanarine;
        this.trajanjeClanarine = trajanjeClanarine;
        this.brTrenutnihKnjiga = brTrenutnihKnjiga;
        this.tipClanstva = tipClanstva;
    }

    public int getIdClan() {
        return idClan;
    }

    public void setIdClan(int idClan) {
        this.idClan = idClan;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public LocalDate getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(LocalDate datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public LocalDate getDatumIstekaClanarine() {
        return datumIstekaClanarine;
    }

    public void setDatumIstekaClanarine(LocalDate datumIstekaClanarine) {
        this.datumIstekaClanarine = datumIstekaClanarine;
    }

    public int getBrTrenutnihKnjiga() {
        return brTrenutnihKnjiga;
    }

    public void setBrTrenutnihKnjiga(int brTrenutnihKnjiga) {
        this.brTrenutnihKnjiga = brTrenutnihKnjiga;
    }

    public TipClanstva getTipClanstva() {
        return tipClanstva;
    }

    public void setTipClanstva(TipClanstva tipClanstva) {
        this.tipClanstva = tipClanstva;
    }

    public int getTrajanjeClanarine() {
        return trajanjeClanarine;
    }

    public void setTrajanjeClanarine(int trajanjeClanarine) {
        this.trajanjeClanarine = trajanjeClanarine;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + ", email: " + email + ", tipClanstva: " + tipClanstva;
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
        final Clan other = (Clan) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO clan (ime, prezime, email, lozinka, datumUclanjenja, datumIstekaClanarine, trajanjeClanarine, brTrenutnihKnjiga, tipClanstva) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
        ps.setDate(5, Date.valueOf(datumUclanjenja));
        ps.setDate(6, Date.valueOf(datumIstekaClanarine));
        ps.setInt(7, trajanjeClanarine);
        ps.setInt(8, brTrenutnihKnjiga);
        ps.setInt(9, tipClanstva.getIdTipClanstva());
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE clan "
                + "SET ime=?, prezime=?, email=?, lozinka=?, datumUclanjenja=?, datumIstekaClanarine=?, trajanjeClanarine=?, brTrenutnihKnjiga=?, tipClanstva=? "
                + "WHERE idClan=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
        ps.setDate(5, Date.valueOf(datumUclanjenja));
        ps.setDate(6, Date.valueOf(datumIstekaClanarine));
        ps.setInt(7, trajanjeClanarine);
        ps.setInt(8, brTrenutnihKnjiga);
        ps.setInt(9, tipClanstva.getIdTipClanstva());
        ps.setInt(10, idClan);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM clan "
                + "WHERE idClan=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idClan);
    }

    @Override
    public String getSelectQuery() {
        if (idClan == 0) {
            return "SELECT * FROM clan c JOIN tip_clanstva tc ON c.tipClanstva = tc.idTipClanstva "
                    + "WHERE c.email=? AND c.lozinka=?";
        } else {
            return "SELECT * FROM clan c JOIN tip_clanstva tc ON c.tipClanstva = tc.idTipClanstva "
                    + "WHERE c.idClan=?";
        }
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        if (idClan == 0) {
            ps.setString(1, email);
            ps.setString(2, lozinka);
        } else {
            ps.setInt(1, idClan);
        }
    }

    @Override
    public Clan createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
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
            return new Clan(rsIdClan, rsIme, rsPrezime, rsEmail, rsLozinka, rsDatumUclanjenja, rsDatumIstekaClanarine, rsTrajanjeClanarine, rsBrTrenutnihKnjiga, tipclanstva);

        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * "
                + "FROM clan c LEFT JOIN tip_clanstva tc ON c.tipClanstva = tc.idTipClanstva";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<Clan> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Clan> lista = new ArrayList<>();
        while (rs.next()) {

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
            lista.add(new Clan(rsIdClan, rsIme, rsPrezime, rsEmail, rsLozinka, rsDatumUclanjenja, rsDatumIstekaClanarine, rsTrajanjeClanarine, rsBrTrenutnihKnjiga, tipclanstva));
        }
        return lista;
    }

}
