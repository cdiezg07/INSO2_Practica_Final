/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Usuarios;

/**
 *
 * @author Mario
 */
@Named
@ViewScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    private Usuarios usuario;

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        usuario = new Usuarios();
    }

    public void Guardar() {
        try {
            usuariosEJB.create(usuario);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se guardó correctamente en base de datos"));

        } catch (Exception e) {
        }
    }

}
