/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;

/**
 *
 * @author kacan
 */
public class KreirajIznajmljivanjeSO extends AbstractSystemOperation<Iznajmljivanje> {

    private List<StavkaIznajmljivanja> stavkeIznajmljivanja;

    public KreirajIznajmljivanjeSO(List<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

    @Override
    protected void validate(Iznajmljivanje iznajmljivanje) throws Exception {
        if (iznajmljivanje == null) {
            throw new IllegalArgumentException("Iznajmljivanje ne sme biti null");
        }
        if (stavkeIznajmljivanja == null || stavkeIznajmljivanja.isEmpty()) {
            throw new IllegalArgumentException("Iznajmljivanje mora imati barem jednu stavku");
        }
    }

    @Override
    protected Object executeOperation(Iznajmljivanje iznajmljivanje) throws Exception {
        int rb = 1;
        int ugovorId = (int) dbb.insert(iznajmljivanje);
        iznajmljivanje.setIdIznajmljivanje(ugovorId);
        for (StavkaIznajmljivanja stavka : stavkeIznajmljivanja) {
            stavka.setRb(rb);
            stavka.setIznajmljivanje(iznajmljivanje);
            dbb.insert(stavka);
            rb++;
        }
        dbb.update(iznajmljivanje.getClan());
        return iznajmljivanje.getIdIznajmljivanje();
    }

}