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
import modelo.Clientes;
import modelo.Direcciones;
import modelo.Usuarios;

/**
 *
 * @author carlos
 */
@Stateless
public class DireccionesFacade extends AbstractFacade<Direcciones> implements DireccionesFacadeLocal {

    @PersistenceContext(unitName = "ikeaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionesFacade() {
        super(Direcciones.class);
    }
    
    @Override
    public List<Direcciones> traerTodas(Clientes usuarioActivo) {

        String consulta = "FROM Direcciones d WHERE d.emailUsuario=:param1";
        
        Query query = em.createQuery(consulta);
        query.setParameter("param1", usuarioActivo);
        
        List<Direcciones> resultado = query.getResultList(); //retorna una lista con todos los coincidentes de la base de datos
        
        if(resultado.size() == 0){
            return null;
        }
        return resultado;
       
    }

}
