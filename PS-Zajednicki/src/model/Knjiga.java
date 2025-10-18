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
public class Knjiga implements Serializable, DomainObject<Knjiga> {

    private int idKnjiga;
    private String naslov;
    private String isbn;
    private String autor;
    private String izdavac;

    public Knjiga() {
    }

    public Knjiga(int idKnjiga, String naslov, String isbn, String autor, String izdavac) {
        this.idKnjiga = idKnjiga;
        this.naslov = naslov;
        this.isbn = isbn;
        this.autor = autor;
        this.izdavac = izdavac;
    }
    
    public Knjiga(String naslov, String isbn, String autor, String izdavac) {
        this.naslov = naslov;
        this.isbn = isbn;
        this.autor = autor;
        this.izdavac = izdavac;
    }

    public int getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    @Override
    public String toString() {
        return naslov + " | " + autor;
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
        final Knjiga other = (Knjiga) obj;
        if (!Objects.equals(this.naslov, other.naslov)) {
            return false;
        }
        return Objects.equals(this.autor, other.autor);
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO knjiga (naslov, isbn, autor, izdavac) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naslov);
        ps.setString(2, isbn);
        ps.setString(3, autor);
        ps.setString(4, izdavac);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE knjiga SET naslov=?, isbn=?, autor=?, izdavac=? "
                + "WHERE idKnjiga=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naslov);
        ps.setString(2, isbn);
        ps.setString(3, autor);
        ps.setString(4, izdavac);
        ps.setInt(5, idKnjiga);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM knjiga "
                + "WHERE idKnjiga=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idKnjiga);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM knjiga "
                + "WHERE idKnjiga=?";

    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idKnjiga);
    }

    @Override
    public Knjiga createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int rsId = rs.getInt("idKnjiga");
            String rsNaslov = rs.getString("naslov");
            String rsIsbn = rs.getString("isbn");
            String rsAutor = rs.getString("autor");
            String rsIzdavac = rs.getString("izdavac");
            return new Knjiga(rsId, rsNaslov, rsIsbn, rsAutor, rsIzdavac);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM knjiga";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<Knjiga> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Knjiga> lista = new ArrayList<>();
        while (rs.next()) {
            int rsId = rs.getInt("idKnjiga");
            String rsNaslov = rs.getString("naslov");
            String rsIsbn = rs.getString("isbn");
            String rsAutor = rs.getString("autor");
            String rsIzdavac = rs.getString("izdavac");

            lista.add(new Knjiga(rsId, rsNaslov, rsIsbn, rsAutor, rsIzdavac));
        }
        return lista;
    }

}