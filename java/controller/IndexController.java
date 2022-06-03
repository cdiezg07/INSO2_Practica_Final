/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    public String verificarUsuario() {

        Usuarios usuEncontrado = null;

        try {
            usuEncontrado = usuarioEJB.verificarUsuario(usuarioABuscar);

        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR AL TRATAR DE VALIDAR EL LOGIN");
            return "index.xhtml";
        }

        if (usuEncontrado == null) {
            return "permisosinsuficientes.xhtml";
        }
        //Colocamos asimismo el usuario en el contexto de la apliacion para que el usuario sea accesible globalmente

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLoggeado", usuEncontrado);

        return "Principal.xhtml";

    }

}
