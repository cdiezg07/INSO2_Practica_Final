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
public class RegistroController implements Serializable{
    
    private Usuarios user;
    private Clientes cliente;
    private Trabajadores trabajador;
    private Administradores admin;
    @EJB
    private UsuariosFacadeLocal userEJB;
    @EJB 
    private ClientesFacadeLocal clienteEJB;
    @EJB
    private TrabajadoresFacadeLocal trabajadorEJB;
    @EJB
    private AdministradoresFacadeLocal administradorEJB;
       
    
    @PostConstruct
    public void init(){
        user = new Usuarios();
        cliente = new Clientes();
        trabajador = new Trabajadores();
        admin = new Administradores();
    }
        
    public void insertarUsuario(){
        try{
            
            System.out.println(user.getNombre()+ user.getTipo());
            user.setTipo("cliente");
            userEJB.create(user);            
            if(user.getTipo()=="cliente"){
                cliente.setEmailCliente(user);
                clienteEJB.create(cliente);
            }else if(user.getTipo()=="trabajador"){
                trabajadorEJB.create(trabajador);
            }else{
                administradorEJB.create(admin);
            }
        }catch(Exception e){
            System.out.println("Error al insertar el usuario "+ e.getMessage());
        }
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }
    
    
}
