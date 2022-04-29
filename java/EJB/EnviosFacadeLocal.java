/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Envios;

/**
 *
 * @author carlos
 */
@Local
public interface EnviosFacadeLocal {

    void create(Envios envios);

    void edit(Envios envios);

    void remove(Envios envios);

    Envios find(Object id);

    List<Envios> findAll();

    List<Envios> findRange(int[] range);

    int count();
    
}
