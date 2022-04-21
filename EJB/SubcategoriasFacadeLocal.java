/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Subcategorias;

/**
 *
 * @author carlos
 */
@Local
public interface SubcategoriasFacadeLocal {

    void create(Subcategorias subcategorias);

    void edit(Subcategorias subcategorias);

    void remove(Subcategorias subcategorias);

    Subcategorias find(Object id);

    List<Subcategorias> findAll();

    List<Subcategorias> findRange(int[] range);

    int count();
    
}
