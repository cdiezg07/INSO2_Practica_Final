/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriasFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import jdk.nashorn.internal.objects.NativeArray;
import modelo.Categorias;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class ObtenerCategoriasController implements Serializable{
    
    private Categorias cat;
    private List<Categorias> listaCat;
    
    @EJB
    private CategoriasFacadeLocal catEJB;
    
    @PostConstruct
    public void init(){
        cat = new Categorias();
    }
        
    public void insertarCategoria(){
        try{
            catEJB.create(cat);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+ e.getMessage());
        }
    }
    
    public List<Categorias> getCategorias(){
        try{
            listaCat = catEJB.findAll();
            for(Categorias cat: listaCat){
                System.out.println("Categorias"+cat.getNombre()); 
            }
            return listaCat;
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+ e.getMessage());
        }
        return null;
    }

    public Categorias getCat() {
        return cat;
    }

    public void setCat(Categorias cat) {
        this.cat = cat;
    }   
    
}
