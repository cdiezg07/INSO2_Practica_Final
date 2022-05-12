/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.SubcategoriasFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categorias;
import modelo.Subcategorias;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class ObtenerSubCategoriasController implements Serializable{
    
    private Subcategorias subcat;
    private List<Subcategorias> listaSubcat;
    
    @EJB
    private SubcategoriasFacadeLocal subcatEJB;
    
    @PostConstruct
    public void init(){
        subcat = new Subcategorias();
    }
        
    public void insertarSubcategoria(){
        try{
            subcatEJB.create(subcat);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+ e.getMessage());
        }
    }
    
    public List<Subcategorias> getSubcategorias(String nombreCat){
       

        try{
            listaSubcat = subcatEJB.findAll();
            List<Subcategorias> listaFinal = new ArrayList<Subcategorias>();
            System.out.println("fsdfasdfasdf"+nombreCat);
            for(Subcategorias sub: listaSubcat){
                System.out.println(sub.getCategoria().getNombre());
                if(sub.getCategoria().getNombre().equals(nombreCat)){
                    listaFinal.add(sub);
                }
            }
            return listaFinal;
            
        }catch(Exception e){
            System.out.println("Error al obtener la subcategoria "+ e.getMessage());
        }
        return null;
    }

//    public Categorias getCat() {
//        return cat;
//    }
//
//    public void setCat(Categorias cat) {
//        this.cat = cat;
//    }   
    
}
