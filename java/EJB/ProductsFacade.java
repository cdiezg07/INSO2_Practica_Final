/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Cesta;
import modelo.Opiniones;
import modelo.Products;

/**
 *
 * @author carlos
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal {

    @PersistenceContext(unitName = "ikeaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }

    @Override
    public List<Opiniones> getOpiniones(Products producto) {
        String consulta = "FROM opiniones o WHERE o.UPC=:param1";
        
        Query query = em.createQuery(consulta);
        query.setParameter("param1", producto);
        
        List<Opiniones> opiniones = query.getResultList(); //retorna una lista con todos los coincidentes de la base de datos
        
        if(opiniones.size() == 0){
            return null;
        }else{
            return opiniones;
        } 
    }
    
}
