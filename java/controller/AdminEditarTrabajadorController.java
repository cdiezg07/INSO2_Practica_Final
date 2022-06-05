/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.TrabajadoresFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Trabajadores;

/**
 *
 * @author Mario
 */
@Named
@ViewScoped
public class AdminEditarTrabajadorController implements Serializable {

    @Inject
    private AdminListarTrabajadoresController vistaAnteriorCongelada;

    //Para poder interactuar con el en la vista 
    private Trabajadores trabTraido;

    @EJB
    private TrabajadoresFacadeLocal trabajadorEJB;

    public Trabajadores getTrabTraido() {
        return trabTraido;
    }

    public void setTrabTraido(Trabajadores trabTraido) {
        this.trabTraido = trabTraido;
    }

    
    @PostConstruct
    public void init() {
        //se tiene que llenar el atributo antes de renderizar la vista para que aparezcan sus datos y los podamos modificar
        this.trabTraido = vistaAnteriorCongelada.getTrabSeleccionado();
    }

    public String guardarTrabajadorModificado() {

        try {

            this.trabajadorEJB.edit(this.trabTraido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El trabajador modificado se ha guardado satisfactoriamente"));

        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR AL INTENTAR GUARDAR EL TRABAJADOR MODIFICADO");

        }
        return "Administracion.xhtml?faces-redirect=true";

    }

}
