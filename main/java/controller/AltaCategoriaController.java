/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriasFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categorias;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class AltaCategoriaController implements Serializable{
    
    private Categorias cat;
    
    @EJB
    private CategoriasFacadeLocal categoriaEJB;
    
    @PostConstruct
    public void init(){
        cat = new Categorias();
    }
        
    public void insertarCategoria(){
        try{
            categoriaEJB.create(cat);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+ e.getMessage());
        }
    }

    public Categorias getCat() {
        return cat;
    }

    public void setCat(Categorias cat) {
        this.cat = cat;
    }
    
    
}
