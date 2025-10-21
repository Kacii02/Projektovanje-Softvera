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
public class IzmeniIznajmljivanjeSO extends AbstractSystemOperation<Iznajmljivanje> {

    private List<StavkaIznajmljivanja> stavkeIznajmljivanja;

    public IzmeniIznajmljivanjeSO(List<StavkaIznajmljivanja> stavkeIznajmljivanja) {
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
        dbb.update(iznajmljivanje);
        for (StavkaIznajmljivanja stavka : stavkeIznajmljivanja) {
            dbb.update(stavka);
        }
        dbb.update(iznajmljivanje.getClan());
        return iznajmljivanje.getIdIznajmljivanje();
    }

}
