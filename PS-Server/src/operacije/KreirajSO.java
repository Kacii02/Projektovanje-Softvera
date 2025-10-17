/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import domain.DomainObject;

/**
 *
 * @author kacan
 * @param <T>
 */
public class KreirajSO<T extends DomainObject<T>> extends AbstractSystemOperation<T> {

    @Override
    protected void validate(T object) throws Exception {
        if (object == null) {
            throw new Exception("Kreirani objekat ne sme biti null.");
        }
    }

    @Override
    protected Object executeOperation(T object) throws Exception {
        return dbb.insert(object);
    }

}
