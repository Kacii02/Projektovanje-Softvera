/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.sql.*;
import baza.DbBroker;
import domain.DomainObject;

/**
 *
 * @author kacan
 * @param <T>
 */
public abstract class AbstractSystemOperation<T extends DomainObject> {

    protected DbBroker dbb;

    public AbstractSystemOperation() {
        dbb = new DbBroker();
    }

    public final Object execute(T object) throws Exception {
        try {
            dbb.connect();
            validate(object);
            Object result = executeOperation(object);
            dbb.commit();
            return result;

        } catch (SQLIntegrityConstraintViolationException e) {
            dbb.rollback();
            throw e; // dupli kljucevi

        } catch (IllegalArgumentException e) {
            dbb.rollback();
            throw e; // validacija objekta

        } catch (SQLException e) {
            dbb.rollback();
            throw e; // sve ostale SQL greske

        } catch (Exception e) {
            dbb.rollback();
            throw e; // sve ostale greske
        } finally {
            dbb.disconnect();
        }
    }

    protected abstract void validate(T object) throws Exception;

    //mora biti implementirana u podklasi
    protected abstract Object executeOperation(T object) throws Exception;
    //mora biti implementirana u podklasi
}
