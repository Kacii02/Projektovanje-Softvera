/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import config.FileReaderConfig;
import domain.DomainObject;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author kacan
 */
public class DbBroker {

    private Connection connection;
    String url;
    String user;
    String password;

    public DbBroker() {
        FileReaderConfig fileReader = new FileReaderConfig();
        try {
            fileReader.readFile("server.config");
            url = fileReader.getConnectionUrl();
            user = fileReader.getUser();
            password = fileReader.getPassword();
        } catch (IOException ex) {
            System.out.println("DBB: Greska prilikom ucitavanja server.config: " + ex.getMessage());
        }
    }

    public void connect() {

        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("DBB: Uspesno povezivanje sa bazom");
        } catch (SQLException ex) {
            System.out.println("DBB: Konekcija sa bazom nije uspela");
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("DBB: Konekcija ja prekinuta");
            }
        } catch (SQLException ex) {
            System.out.println("DBB: Konekcija nije prekinuta");
        }
    }

    public void commit() {
        try {
            if (connection != null) {
                connection.commit();
                System.out.println("DBB: Commit uspesno izvrsen.");
            }
        } catch (SQLException e) {
            System.out.println("DBB: Greska prilikom commit-a: " + e.getMessage());
        }
    }

    public void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
                System.out.println("DBB: Rollback izvrsen.");
            }
        } catch (SQLException e) {
            System.out.println("DBB: Greska prilikom rollback-a: " + e.getMessage());
        }
    }

    public <T extends DomainObject<T>> Object insert(T obj) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(obj.getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            obj.fillInsertStatement(ps);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            System.out.println("DBB: Doslo je do greske prilikom INSERT upita: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    public <T extends DomainObject<T>> Object update(T obj) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(obj.getUpdateQuery());
            obj.fillUpdateStatement(ps);
            int affectedRows = ps.executeUpdate();
            return affectedRows;
        } catch (SQLException e) {
            System.out.println("DBB: Doslo je do greske prilikom UPDATE upita: " + e.getMessage());
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public <T extends DomainObject<T>> Object delete(T obj) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(obj.getDeleteQuery());
            obj.fillDeleteStatement(ps);
            int affectedRows = ps.executeUpdate();
            return affectedRows;
        } catch (SQLException e) {
            System.out.println("DBB: Doslo je do greske prilikom DELETE upita: " + e.getMessage());
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public <T extends DomainObject<T>> Object select(T obj) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(obj.getSelectQuery());
            obj.fillSelectStatement(ps);
            rs = ps.executeQuery();
            return obj.createFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("DBB: Doslo je do greske prilikom SELECT upita: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    public <T extends DomainObject<T>> Object selectAll(T obj) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(obj.getSelectAllQuery());
            obj.fillSelectAllStatement(ps);
            rs = ps.executeQuery();
            return obj.createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("DBB: Doslo je do greske prilikom SELECT ALL upita: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

}
