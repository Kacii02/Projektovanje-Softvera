/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pomocni;

import java.io.Serializable;
import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;

/**
 *
 * @author kacan
 */
public class IznajmljivanjeSaStavkama implements Serializable{
    private Iznajmljivanje iznajmljivanje;
    private List<StavkaIznajmljivanja> stavke;

    public IznajmljivanjeSaStavkama() {
    }

    public IznajmljivanjeSaStavkama(Iznajmljivanje iznajmljivanje, List<StavkaIznajmljivanja> stavke) {
        this.iznajmljivanje = iznajmljivanje;
        this.stavke = stavke;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "IznajmljivanjeSaStavkama{" + "iznajmljivanje=" + iznajmljivanje + ", stavke=" + stavke + '}';
    }
    
    
    
}
