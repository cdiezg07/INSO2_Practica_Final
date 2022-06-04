/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CestaFacadeLocal;
import EJB.ClientesFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Cesta;
import modelo.Clientes;
import modelo.Usuarios;

/**
 *
 * @author Mario
 */
@Named
@ViewScoped
public class CestaController implements Serializable{
    
    
    private Usuarios usuarioActivo;
    private Clientes infoEspCliente;
    private List<Cesta> cestaUsuarioActivo;
    
    private float subtotal;
    
    
    @EJB
    private CestaFacadeLocal cestaEJB;
       @EJB
    private ClientesFacadeLocal clientesEJB;

    public List<Cesta> getCestaUsuarioActivo() {
        return cestaUsuarioActivo;
    }

    public void setCestaUsuarioActivo(List<Cesta> cestaUsuarioActivo) {
        this.cestaUsuarioActivo = cestaUsuarioActivo;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

   
       
    
    
    @PostConstruct
    public void init() {
        
        //Según el usuario que esté loggeado se trae lo que tenga en la cesta
       this.usuarioActivo = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoggeado");
       this.infoEspCliente = this.clientesEJB.find(this.usuarioActivo.getEmail());
              
       this.cestaUsuarioActivo = this.cestaEJB.getProductos(this.infoEspCliente);
       
       this.calcularSubtotal();
       
    }

    public void calcularSubtotal() {

        float val = 0;
        
        for (int i = 0; i < this.cestaUsuarioActivo.size(); i++) {
            val = val + (this.cestaUsuarioActivo.get(i).getUPC().getPriceNumeral() * this.cestaUsuarioActivo.get(i).getCantidad());           
            
        }      
         

        this.subtotal = val;
    }
    
    
    public void eliminarDeCesta(Cesta cestaABorrar){
        //se elimina de la cesta de la base de datos y de la lista local
        cestaEJB.remove(cestaABorrar);
        System.out.println(this.cestaUsuarioActivo.size());                
        this.cestaUsuarioActivo.remove(cestaABorrar);
System.out.println(this.cestaUsuarioActivo.size());
    }
    
    
    
    
    
    
    
    
    
    
}
