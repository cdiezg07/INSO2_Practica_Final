/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Subcategorias;

/**
 *
 * @author carlos
 */
@Stateless
public class SubcategoriasFacade extends AbstractFacade<Subcategorias> implements SubcategoriasFacadeLocal {

    @PersistenceContext(unitName = "ikeaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubcategoriasFacade() {
        super(Subcategorias.class);
    }
    
}
