/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DbBroker;
import domain.DomainObject;
import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.IzmeniIznajmljivanjeSO;
import operacije.IzmeniSO;
import operacije.KreirajIznajmljivanjeSO;
import operacije.KreirajSO;
import operacije.ObrisiSO;
import operacije.VratiSO;
import operacije.VratiSveSO;

/**
 *
 * @author kacan
 */
public class Controller {

    private static Controller instance;
    private DbBroker dbb;

    public Controller() {
        dbb = new DbBroker();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public <T extends DomainObject<T>> T vrati(T object) {
        try {
            return (T) new VratiSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("C: Greska prilikom izvrsavanja vratiSO: " + ex.getMessage());
        }
        return null;
    }

    public <T extends DomainObject<T>> List<T> vratiSve(T object) {
        try {
            return (List<T>) new VratiSveSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("C: Greska prilikom izvrsavanja vratiSveSO: " + ex.getMessage());
        }
        return null;
    }

    public <T extends DomainObject<T>> int dodaj(T object) {
        try {
            return (int) new KreirajSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("C: Greska prilikom izvrsavanja dodajSO: " + ex.getMessage());
        }
        return 0;
    }

    public <T extends DomainObject<T>> int izmeni(T object) {
        try {
            return (int) new IzmeniSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("C: Greska prilikom izvrsavanja izmeniSO: " + ex.getMessage());
        }
        return 0;
    }

    public <T extends DomainObject<T>> int obrisi(T object) {
        try {
            return (int) new ObrisiSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("C: Greska prilikom izvrsavanja obrisiSO: " + ex.getMessage());
        }
        return 0;
    }
    
    public int kreirajIznajmljivanje(Iznajmljivanje i, List<StavkaIznajmljivanja> listaStavki) {
        try {
            return (int) new KreirajIznajmljivanjeSO(listaStavki).execute(i);
        } catch (Exception ex) {
            System.out.println("Greska prilikom izvrsavanja KreirajIznajmljivanjeSO: " + ex.getMessage());
        }
        return 0;
    }
    
    public int izmeniIznajmljivanje(Iznajmljivanje i, List<StavkaIznajmljivanja> listaStavki) {
        try {
            return (int) new IzmeniIznajmljivanjeSO(listaStavki).execute(i);
        } catch (Exception ex) {
            System.out.println("Greska prilikom izvrsavanja IzmeniIznajmljivanjeSO: " + ex.getMessage());
        }
        return 0;
    }
}
