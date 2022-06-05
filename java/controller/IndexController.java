/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UsuariosFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuarios;

/**
 *
 * @author Mario
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    @EJB
    private UsuariosFacadeLocal usuarioEJB;
    private Usuarios usuarioABuscar;

    public Usuarios getUsuarioABuscar() {
        return usuarioABuscar;
    }

    public void setUsuarioABuscar(Usuarios usuarioABuscar) {
        this.usuarioABuscar = usuarioABuscar;
    }

    //Necesario puesto que de lo contrario el usuario no existe y no se le pueden setear desde la vista los valores
    @PostConstruct
    public void init() {
        usuarioABuscar = new Usuarios();
    }

    public void verificarUsuario() {

        Usuarios usuEncontrado = null;

        try {
            usuEncontrado = usuarioEJB.verificarUsuario(usuarioABuscar);

        } catch (Exception e) {
            try {
                System.out.println("SE HA PRODUCIDO UN ERROR AL TRATAR DE VALIDAR EL LOGIN");
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }

         
        }

        if (usuEncontrado == null) {
                   
            addMessageError("Error", "El email y contraseña introducidos no son correctos");            
            return;
        }
        //Colocamos asimismo el usuario en el contexto de la apliacion para que el usuario sea accesible globalmente

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLoggeado", usuEncontrado);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
     public void addMessageError(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
