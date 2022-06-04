/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Clientes;
import modelo.Direcciones;
import modelo.Usuarios;

/**
 *
 * @author carlos
 */
@Local
public interface DireccionesFacadeLocal {

    void create(Direcciones direcciones);

    void edit(Direcciones direcciones);

    void remove(Direcciones direcciones);

    Direcciones find(Object id);

    List<Direcciones> findAll();

    List<Direcciones> findRange(int[] range);

    int count();

    public List<Direcciones> traerTodas(Clientes usuarioActivo);
    
}
