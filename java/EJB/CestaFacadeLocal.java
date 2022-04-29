/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Cesta;

/**
 *
 * @author carlos
 */
@Local
public interface CestaFacadeLocal {

    void create(Cesta cesta);

    void edit(Cesta cesta);

    void remove(Cesta cesta);

    Cesta find(Object id);

    List<Cesta> findAll();

    List<Cesta> findRange(int[] range);

    int count();
    
}
