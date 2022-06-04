/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.AdministradoresFacadeLocal;
import EJB.ClientesFacadeLocal;
import EJB.TrabajadoresFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Administradores;
import modelo.Clientes;
import modelo.Trabajadores;
import modelo.Usuarios;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class RegistroController implements Serializable {

    private Usuarios user;
    private Clientes cliente;
    private boolean prueba = false;

    @EJB
    private ClientesFacadeLocal clienteEJB;

    @PostConstruct
    public void init() {
        user = new Usuarios();
        cliente = new Clientes();

        //A la carga de la pagina se haria la comprobacion de si es utrabajador el usuario guardado en la sesion. Si lo es con un if se cambia la variable booleana a true y entonces el binding en la vista renderiza las partes visibles a mayores solo para el
        this.prueba = true;

    }

    public void insertarUsuario() {
        try {
            user.setTipo("cliente");
            cliente.setEmailCliente(user);
            clienteEJB.create(cliente);
            FacesContext.getCurrentInstance().getExternalContext().redirect("LogIn.xhtml");
        } catch (Exception e) {
            System.out.println("Error al insertar el usuario " + e.getMessage());
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Registro.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }

    public boolean isPrueba() {
        return prueba;
    }

    public void setPrueba(boolean prueba) {
        this.prueba = prueba;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

}
