/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.TrabajadoresFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
        return "AdminEditarTrabajador.xhtml";
    }

    public void eliminarTrabajadorSeleccionado(Trabajadores trabPinchadoVista) {
        //Trabajadores trabPinchadoVista = this.listaDeTrabajadores.get(0);

        try {
            this.trabajadorEJB.remove(trabPinchadoVista);
        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR A LA HORA DE ELIMINAR EL TABAJADOR");
        }
    }

    public void guardarTrabajadorCreado() {

       // this.nuevoTrabajadorGen.setEmail("correoElectronico");
       // this.nuevoTrabajadorGen.setNombre("nombre");
       // this.nuevoTrabajadorGen.setApellidos("Apellidos");
       // this.nuevoTrabajadorGen.setPassword("Contraseña");
        //this.nuevoTrabajadorGen.setTipo("trabajador");

        //this.nuevoTrabajadorEsp.setEmailTrabajador(this.nuevoTrabajadorGen);
        //this.nuevoTrabajadorEsp.setDNI("00004455P");
        //this.nuevoTrabajadorEsp.setFecha_nacimiento(new Date(1990, 12, 12));
        //this.nuevoTrabajadorEsp.setNum_telefono("666666666");

        try {
            this.trabajadorEJB.create(this.nuevoTrabajadorEsp);
        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR A LA HORA DE GUARDAR EL TABAJADOR");
        }

    }

}
