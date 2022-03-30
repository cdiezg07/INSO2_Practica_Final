/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Publicacaciones;

/**
 *
 * @author carlos
 */
@Local
public interface PublicacacionesFacadeLocal {

    void create(Publicacaciones publicacaciones);

    void edit(Publicacaciones publicacaciones);

    void remove(Publicacaciones publicacaciones);

    Publicacaciones find(Object id);

    List<Publicacaciones> findAll();

    List<Publicacaciones> findRange(int[] range);

    int count();
    
}
