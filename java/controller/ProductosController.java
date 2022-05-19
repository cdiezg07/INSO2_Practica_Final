/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ProductsFacadeLocal;
import EJB.SubcategoriasFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categorias;
import modelo.Products;
import modelo.Subcategorias;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class ProductosController implements Serializable{
    
    private Products producto;
    private List<Products> listaProductos;
    
    @EJB
    private ProductsFacadeLocal productsEJB;
    
    @PostConstruct
    public void init(){
        producto = new Products();
    }
        
    public void insertarSubcategoria(){
        try{
            productsEJB.create(producto);
        }catch(Exception e){
            System.out.println("Error al insertar el producto "+ e.getMessage());
        }
    }
    
    public List<Products> getProducts(String nombreSubCategoria){
       

        try{
            listaProductos = productsEJB.findAll();
            List<Products> listaFinal = new ArrayList<Products>();
            System.out.println("fsdfasdfasdf"+nombreSubCategoria);
            for(Products pro: listaProductos){
                System.out.println(pro.getSubcategoria().getNombreSubcategoria());
                if(pro.getSubcategoria().getNombreSubcategoria().equals(nombreSubCategoria)){
                    listaFinal.add(pro);
                }
            }
            return listaFinal;
            
        }catch(Exception e){
            System.out.println("Error al obtener el producto "+ e.getMessage());
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
