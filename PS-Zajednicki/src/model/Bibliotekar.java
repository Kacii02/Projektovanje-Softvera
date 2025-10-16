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

/**
 *
 * @author kacan
 */
public class Bibliotekar implements Serializable, DomainObject<Bibliotekar> {

    private int idBibliotekar;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;

    public Bibliotekar() {
    }

    public Bibliotekar(int idBibliotekar, String ime, String prezime, String email, String lozinka) {
        this.idBibliotekar = idBibliotekar;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
    }

    public int getIdBibliotekar() {
        return idBibliotekar;
    }

    public void setIdBibliotekar(int idBibliotekar) {
        this.idBibliotekar = idBibliotekar;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO bibliotekar (ime, prezime, email, lozinka) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE bibliotekar SET ime=?, prezime=?, email=?, lozinka=? "
                + "WHERE idBibliotekar=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
        ps.setInt(5, idBibliotekar);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM bibliotekar "
                + "WHERE idBibliotekar=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idBibliotekar);
    }

    @Override
    public String getSelectQuery() {
        if (idBibliotekar == 0) {
            return "SELECT * FROM bibliotekar WHERE email=? AND lozinka=?";
        } else {
            return "SELECT * FROM bibliotekar WHERE idBibliotekar=?";
        }
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        if (idBibliotekar == 0) {
            ps.setString(1, email);
            ps.setString(2, lozinka);
        } else {
            ps.setInt(1, idBibliotekar);
        }
    }

    @Override
    public Bibliotekar createFromResultSet(ResultSet rs) throws SQLException {
         if (rs.next()) {
            int rsIdBibliotekar = rs.getInt("idBibliotekar");
            String rsIme = rs.getString("ime");
            String rsPrezime = rs.getString("prezime");
            String rsEmail = rs.getString("email");
            String rsLozinka = rs.getString("lozinka");
            
            return new Bibliotekar(rsIdBibliotekar, rsIme, rsPrezime, rsEmail, rsLozinka);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
       return "SELECT * FROM bibliotekar";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<Bibliotekar> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Bibliotekar> lista = new ArrayList<>();
        while (rs.next()) {
            int rsIdBibliotekar = rs.getInt("idBibliotekar");
            String rsIme = rs.getString("ime");
            String rsPrezime = rs.getString("prezime");
            String rsEmail = rs.getString("email");
            String rsLozinka = rs.getString("lozinka");
            
            lista.add(new Bibliotekar(rsIdBibliotekar, rsIme, rsPrezime, rsEmail, rsLozinka));
        }
        return lista;
    }

}
