/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuarios;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class AltaUsuarioController implements Serializable{
    
    private Usuarios user;
    
    @EJB
    private UsuariosFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        user = new Usuarios();
    }
        
    public void insertarUsuario(){
        try{
            usuarioEJB.create(user);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+ e.getMessage());
        }
    }

    public Usuarios getUser() {
        return user;
    }

    public void setCat(Usuarios user) {
        this.user = user;
    }
    
    
}

