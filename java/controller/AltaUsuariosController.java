/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package controller;
//
//import EJB.CategoriasFacadeLocal;
//import EJB.UsuariosFacadeLocal;
//import java.io.Serializable;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import modelo.Categorias;
//import modelo.Usuarios;
//
///**
// *
// * @author carlos
// */
//@Named
//@ViewScoped
//public class AltaUsuariosController implements Serializable{
//    
//    private Usuarios user;
//    
//    @EJB
//    private UsuariosFacadeLocal usuarioEJB;
//    
//    @PostConstruct
//    public void init(){
//        user = new Usuarios();
//    }
//    
//    public void insertarUsuario(){
//        try{
//            usuarioEJB.create(user);
//        }catch(Exception e){
//            System.out.println("Error al insertar el usuario "+ e.getMessage());
//        }
//    }
//
//    public Usuarios getUser() {
//        return user;
//    }
//
//    public void setUser(Usuarios user) {
//        this.user = user;
//    }   
//    
//}
