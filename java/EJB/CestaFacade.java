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
import modelo.Clientes;
import modelo.Products;

/**
 *
 * @author carlos
 */
@Stateless
public class CestaFacade extends AbstractFacade<Cesta> implements CestaFacadeLocal {

    @PersistenceContext(unitName = "ikeaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CestaFacade() {
        super(Cesta.class);
    }

    @Override
    public boolean yaExisteProducto(Products UPC, Clientes emailCliente) {
        String consulta = "FROM Cesta c WHERE c.emailUsuario=:param1 and c.upc=:param2";
        
        Query query = em.createQuery(consulta);
        query.setParameter("param1", emailCliente);
        query.setParameter("param2", UPC);
        
        List<Cesta> resultado = query.getResultList(); //retorna una lista con todos los coincidentes de la base de datos
        
        if(resultado.size() == 0){
            return false;
        }else{
            return true;
        }    
    }
    
    @Override
    public List<Cesta> getProductos(Clientes emailCliente){
        String consulta = "FROM Cesta c WHERE c.emailUsuario=:param1";
        
        Query query = em.createQuery(consulta);
        query.setParameter("param1", emailCliente);
        
        List<Cesta> cesta = query.getResultList(); //retorna una lista con todos los coincidentes de la base de datos
        
        if(cesta.size() == 0){
            return null;
        }else{
            return cesta;
        }     
        
    }
    
        @Override
        public void eliminarCesta(Clientes emailCliente){
            String consulta = "DELETE FROM Cesta c WHERE c.emailUsuario=:param1";
        
        Query query = em.createQuery(consulta);
        query.setParameter("param1", emailCliente);
        
        query.executeUpdate();
         
        }

        

}
