/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CestaFacadeLocal;
import EJB.ClientesFacadeLocal;
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
import modelo.Cesta;
import modelo.Clientes;
import modelo.Products;
import modelo.Subcategorias;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class SubcategoriasController implements Serializable{
    
    private Subcategorias subcat;
    private List<Subcategorias> listaSubcat;
    private Clientes cliente;
    
    @EJB
    private SubcategoriasFacadeLocal subcatEJB;
    @EJB
    private ProductsFacadeLocal productsEJB;
    @EJB
    private CestaFacadeLocal cestaEJB;
    @EJB
    private ClientesFacadeLocal clienteEJB;
    
    private Products producto;
    private List<Products> listaProductos;
    private Cesta cesta;
    
    
    @PostConstruct
    public void init(){
        subcat = new Subcategorias();
        producto = new Products();
        cesta = new Cesta();
        cliente = new Clientes();
        
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
            System.out.println("Error al obtener los productos "+ e.getMessage());
        }
        return null;
    }
    
    public Products getProduct(String UPC){
      
        try{
            producto = productsEJB.find(UPC);
            
        }catch(Exception e){
            System.out.println("Error al obtener el producto "+ e.getMessage());
        }
        return producto;
    }
 
    public void insertarProductsCesta(String UPC, String emailCliente){
        try{
            cliente = clienteEJB.find(emailCliente);
            producto = getProduct(UPC);
            
            
            if(!cestaEJB.yaExisteProducto(producto, cliente)){
                cesta.setUPC(producto);
                cesta.setEmailUsuario(cliente);
                System.out.println("La cesta es");
                System.out.println(cesta.getUPC());
                System.out.println(cesta.getEmailUsuario());
                cesta.setCantidad(3);
                cestaEJB.create(cesta);
            }else{
                System.out.println("Ya esta en la cesta");
            }
                
            
                
        }catch(Exception e){
            System.out.println("Error al insertar los productos en la cesta "+ e.getMessage());
        }
    }

//    public Categorias getCat() {
//        return cat;
//    }
//
//    public void setCat(Categorias cat) {
//        this.cat = cat;
//    }   
    
}
