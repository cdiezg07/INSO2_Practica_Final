/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Opiniones;

/**
 *
 * @author carlos
 */
@Local
public interface OpinionesFacadeLocal {

    void create(Opiniones opiniones);

    void edit(Opiniones opiniones);

    void remove(Opiniones opiniones);

    Opiniones find(Object id);

    List<Opiniones> findAll();

    List<Opiniones> findRange(int[] range);

    int count();
    
}
