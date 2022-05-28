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
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    private Trabajadores trabajador;
    private Administradores admin;
    private boolean prueba = false;

    @EJB
    private UsuariosFacadeLocal userEJB;
    @EJB
    private ClientesFacadeLocal clienteEJB;
    @EJB
    private TrabajadoresFacadeLocal trabajadorEJB;
    @EJB
    private AdministradoresFacadeLocal administradorEJB;

    @PostConstruct
    public void init() {
        user = new Usuarios();
        cliente = new Clientes();
        trabajador = new Trabajadores();
        admin = new Administradores();
        this.prueba = true;

    }

    public void insertarUsuario() {
        try {

            System.out.println(user.getNombre() + user.getTipo());
            user.setTipo("admin");
            userEJB.create(user);
            if (user.getTipo() == "cliente") {
                cliente.setDireccion("Calle las vergas 23");
                //No nulos
                cliente.setFecha_nacimiento(new Date(1, 2, 2090));
                cliente.setEmailCliente(user);
                cliente.setNumero_telefono("64541654");
                clienteEJB.create(cliente);
            } else if (user.getTipo() == "trabajador") {
                //No nulos
                trabajador.setDNI("27347823Y");
                trabajador.setFecha_nacimiento(new Date(1, 3, 1000));
                trabajador.setNum_telefono("2983924");
                trabajador.setEmailTrabajador(user);
                trabajadorEJB.create(trabajador);
            } else {
                admin.setDNI("123423y");
                admin.setEmailAdministradores(user);
                administradorEJB.create(admin);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar el usuario " + e.getMessage());
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
}
