/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.TrabajadoresFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Trabajadores;
import modelo.Usuarios;

/**
 *
 * @author Mario
 */
@Named
@RequestScoped
public class AdminListarTrabajadoresController implements Serializable {

    //Para comunicar con la vista siguiente el trabajador que se va a modificar
    private Trabajadores trabSeleccionado;

    //Lista de los trabajadores a disposicion del admin para modificar
    private List<Trabajadores> listaDeTrabajadores;

    private Trabajadores nuevoTrabajadorEsp;
    private Usuarios nuevoTrabajadorGen;

    @EJB
    private TrabajadoresFacadeLocal trabajadorEJB;

    public Trabajadores getTrabSeleccionado() {
        return trabSeleccionado;
    }

    public void setTrabSeleccionado(Trabajadores trabSeleccionado) {
        this.trabSeleccionado = trabSeleccionado;
    }

    public List<Trabajadores> getListaDeTrabajadores() {
        return listaDeTrabajadores;
    }

    public void setListaDeTrabajadores(List<Trabajadores> listaDeTrabajadores) {
        this.listaDeTrabajadores = listaDeTrabajadores;
    }

    public Trabajadores getNuevoTrabajadorEsp() {
        return nuevoTrabajadorEsp;
    }

    public void setNuevoTrabajadorEsp(Trabajadores nuevoTrabajadorEsp) {
        this.nuevoTrabajadorEsp = nuevoTrabajadorEsp;
    }

    public Usuarios getNuevoTrabajadorGen() {
        return nuevoTrabajadorGen;
    }

    public void setNuevoTrabajadorGen(Usuarios nuevoTrabajadorGen) {
        this.nuevoTrabajadorGen = nuevoTrabajadorGen;
    }
    
    

    @PostConstruct
    public void init() {
        this.trabSeleccionado = new Trabajadores();
        this.listaDeTrabajadores = new ArrayList<Trabajadores>();
        this.nuevoTrabajadorEsp = new Trabajadores();
        this.nuevoTrabajadorGen = new Usuarios();
        //solicita el listado de todos los trabajadores 
        this.listaDeTrabajadores = trabajadorEJB.findAll();

    }

    public String establecerTrabajadorSeleccionado(Trabajadores trabPinchadoVista) {
        
            // Trabajadores trabPinchadoVista = this.listaDeTrabajadores.get(0);
            this.trabSeleccionado = trabPinchadoVista;
            return "./EditarTrabajador.xhtml";
        


    }

    public void eliminarTrabajadorSeleccionado(Trabajadores trabPinchadoVista) {

        try {
            this.trabajadorEJB.remove(trabPinchadoVista);

        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR A LA HORA DE ELIMINAR EL TABAJADOR");
        }
        this.init();
    }

    public void guardarTrabajadorCreado() {

        try {
            this.nuevoTrabajadorGen.setTipo("trabajador");
            this.nuevoTrabajadorEsp.setEmailTrabajador(this.nuevoTrabajadorGen);
            this.trabajadorEJB.create(this.nuevoTrabajadorEsp);
        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR A LA HORA DE GUARDAR EL TABAJADOR");
        }
        this.init();
    }

}
