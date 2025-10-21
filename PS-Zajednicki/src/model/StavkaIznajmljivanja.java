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
public class StavkaIznajmljivanja implements Serializable, DomainObject<StavkaIznajmljivanja> {

    private int rb;
    private Iznajmljivanje iznajmljivanje;
    private Knjiga knjiga;
    private String napomena;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int rb, Iznajmljivanje iznajmljivanje, Knjiga knjiga, String napomena) {
        this.rb = rb;
        this.iznajmljivanje = iznajmljivanje;
        this.knjiga = knjiga;
        this.napomena = napomena;
    }

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, Knjiga knjiga, String napomena) {
        this.iznajmljivanje = iznajmljivanje;
        this.knjiga = knjiga;
        this.napomena = napomena;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "StavkaIznajmljivanja{" + "rb=" + rb + ", iznajmljivanje=" + iznajmljivanje + ", knjiga=" + knjiga + ", napomena=" + napomena + '}';
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
        final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        return Objects.equals(this.knjiga, other.knjiga);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO stavka_iznajmljivanja (rb, iznajmljivanje, knjiga, napomena) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, rb);
        ps.setInt(2, iznajmljivanje.getIdIznajmljivanje());
        ps.setInt(3, knjiga.getIdKnjiga());
        ps.setString(4, napomena);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE stavka_iznajmljivanja "
                + "SET knjiga=?, napomena=? "
                + "WHERE iznajmljivanje=? AND rb=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, knjiga.getIdKnjiga());
        ps.setString(2, napomena);
        ps.setInt(3, iznajmljivanje.getIdIznajmljivanje());
        ps.setInt(4, rb);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM stavka_iznajmljivanja "
                + "WHERE iznajmljivanje=? AND rb=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, iznajmljivanje.getIdIznajmljivanje());
        ps.setInt(2, rb);
    }

    @Override
    public String getSelectQuery() {
        return """
           SELECT si.iznajmljivanje, si.rb, si.napomena,
                  k.idKnjiga, k.naslov, k.isbn, k.autor, k.izdavac
           FROM stavka_iznajmljivanja si
           JOIN knjiga k ON si.knjiga = k.idKnjiga
           WHERE si.iznajmljivanje = ? AND si.rb = ?
           """;
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, iznajmljivanje.getIdIznajmljivanje());
        ps.setInt(2, rb);
    }

    @Override
    public StavkaIznajmljivanja createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int rsIznajmljivanje = rs.getInt("si.iznajmljivanje");
            int rsRb = rs.getInt("si.rb");
            String rsNapomena = rs.getString("si.napomena");

            int idKnjiga = rs.getInt("k.idKnjiga");
            String naslov = rs.getString("k.naslov");
            String isbn = rs.getString("k.isbn");
            String autor = rs.getString("k.autor");
            String izdavac = rs.getString("k.izdavac");

            Knjiga rsknjiga = new Knjiga(idKnjiga, naslov, isbn, autor, izdavac);
            Iznajmljivanje izn = new Iznajmljivanje();
            izn.setIdIznajmljivanje(rsIznajmljivanje);

            return new StavkaIznajmljivanja(rsRb, izn, rsknjiga, rsNapomena);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        if (iznajmljivanje == null) {
            return """
           SELECT si.iznajmljivanje, si.rb, si.napomena,
                  k.idKnjiga, k.naslov, k.isbn, k.autor, k.izdavac
           FROM stavka_iznajmljivanja si
           JOIN knjiga k ON si.knjiga = k.idKnjiga
           """;
        } else {
            return """
            SELECT si.iznajmljivanje, si.rb, si.napomena,
                  k.idKnjiga, k.naslov, k.isbn, k.autor, k.izdavac
            FROM stavka_iznajmljivanja si
            JOIN knjiga k ON si.knjiga = k.idKnjiga
            WHERE si.iznajmljivanje = ?
            """;
        }
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        if(iznajmljivanje!=null){
             ps.setLong(1, iznajmljivanje.getIdIznajmljivanje());
        }
    }

    @Override
    public List<StavkaIznajmljivanja> createListFromResultSet(ResultSet rs) throws SQLException {
        List<StavkaIznajmljivanja> lista = new ArrayList<>();
        while (rs.next()) {
            int rsIznajmljivanje = rs.getInt("si.iznajmljivanje");
            int rsRb = rs.getInt("si.rb");
            String rsNapomena = rs.getString("si.napomena");

            int idKnjiga = rs.getInt("k.idKnjiga");
            String naslov = rs.getString("k.naslov");
            String isbn = rs.getString("k.isbn");
            String autor = rs.getString("k.autor");
            String izdavac = rs.getString("k.izdavac");

            Knjiga rsKnjiga = new Knjiga(idKnjiga, naslov, isbn, autor, izdavac);
            Iznajmljivanje izn = new Iznajmljivanje();
            izn.setIdIznajmljivanje(rsIznajmljivanje);

            lista.add(new StavkaIznajmljivanja(rsRb, izn, rsKnjiga, rsNapomena));
        }
        return lista;
    }

}
