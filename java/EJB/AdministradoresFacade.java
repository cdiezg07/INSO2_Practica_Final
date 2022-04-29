/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Administradores;

/**
 *
 * @author carlos
 */
@Stateless
public class AdministradoresFacade extends AbstractFacade<Administradores> implements AdministradoresFacadeLocal {

    @PersistenceContext(unitName = "ikeaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradoresFacade() {
        super(Administradores.class);
    }
    
}
