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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kacan
 */
public class TipClanstva implements Serializable, DomainObject<TipClanstva> {

    private int idTipClanstva;
    private String naziv;
    private int maxIznajmljenihKnjiga;

    public TipClanstva() {
    }

    public TipClanstva(int idTipClanstva, String naziv, int maxIznajmljenihKnjiga) {
        this.idTipClanstva = idTipClanstva;
        this.naziv = naziv;
        this.maxIznajmljenihKnjiga = maxIznajmljenihKnjiga;
    }

    public int getIdTipClanstva() {
        return idTipClanstva;
    }

    public void setIdTipClanstva(int idTipClanstva) {
        this.idTipClanstva = idTipClanstva;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMaxIznajmljenihKnjiga() {
        return maxIznajmljenihKnjiga;
    }

    public void setMaxIznajmljenihKnjiga(int maxIznajmljenihKnjiga) {
        this.maxIznajmljenihKnjiga = maxIznajmljenihKnjiga;
    }

    @Override
    public String toString() {
        return naziv;
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
        final TipClanstva other = (TipClanstva) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO tip_clanstva (naziv, maxIznajmljenihKnjiga) "
                + "VALUES (?,?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naziv);
        ps.setInt(2, maxIznajmljenihKnjiga);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE tip_clanstva "
                + "SET naziv=?, maxIznajmljenihKnjiga=? "
                + "WHERE idTipClanstva=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naziv);
        ps.setInt(2, maxIznajmljenihKnjiga);
        ps.setInt(3, idTipClanstva);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM tip_clanstva "
                + "WHERE idTipClanstva=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idTipClanstva);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM tip_clanstva "
                + "WHERE idTipClanstva=?";
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idTipClanstva);
    }

    @Override
    public TipClanstva createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int rsidTipClanstva = rs.getInt("idTipClanstva");
            String rsNaziv = rs.getString("naziv");
            int rsMaxIznajmljenihKnjiga = rs.getInt("maxIznajmljenihKnjiga");

            return new TipClanstva(rsidTipClanstva, rsNaziv, rsMaxIznajmljenihKnjiga);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM tip_clanstva";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri  
    }

    @Override
    public List<TipClanstva> createListFromResultSet(ResultSet rs) throws SQLException {
        List<TipClanstva> lista = new ArrayList<>();
        while (rs.next()) {
            int rsidTipClanstva = rs.getInt("idTipClanstva");
            String rsNaziv = rs.getString("naziv");
            int rsMaxIznajmljenihKnjiga = rs.getInt("maxIznajmljenihKnjiga");

            lista.add(new TipClanstva(rsidTipClanstva, rsNaziv, rsMaxIznajmljenihKnjiga));
        }
        return lista;
    }

}
