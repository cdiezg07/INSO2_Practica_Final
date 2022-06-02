/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ClientesFacadeLocal;
import EJB.OpinionesFacadeLocal;
import EJB.ProductsFacadeLocal;
import EJB.SubcategoriasFacadeLocal;
import EJB.ValoracionFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categorias;
import modelo.Cesta;
import modelo.Clientes;
import modelo.Opiniones;
import modelo.Products;
import modelo.Subcategorias;
import modelo.Usuarios;
import modelo.Valoracion;

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
    @EJB
    private ClientesFacadeLocal clientesEJB;
    @EJB
    private OpinionesFacadeLocal opinionesEJB;
    @EJB
    private Clientes clienteEJB;
    @EJB
    private ValoracionFacadeLocal valoracionEJB;
    private Valoracion valoracion;
    private Opiniones opinion;
    private Clientes cliente;
    private Usuarios usu;
    
    @PostConstruct
    public void init(){
        producto = new Products();
        usu = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoggeado");
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
    
    public void crearOpinion(){
        Products producto;
        if(usu.getEmail()==null){
            System.out.println("No hay usuario logueado");
        }else{
            cliente = clienteEJB.find(usu.getEmail());
            opinion.setComentario("Es la leche este producto");
            opinion.setEmailCliente(cliente);
            opinion.setFecha(new Date("07/07/2000"));
            opinion.setPuntuacion(0);
//            opinion.setUPC(producto);
            try{
                 opinionesEJB.create(opinion);
            }catch(Exception e){
                System.out.println("Error al insertar el producto "+ e.getMessage());
            }
            
        }
        
    }
    
    public void crearValoracion(){
        Products producto;
        if(usu.getEmail()==null){
            System.out.println("No hay usuario logueado");
        }else{
            cliente = clienteEJB.find(usu.getEmail());
            valoracion.setEmailCliente(cliente);
            valoracion.setEstrellas(3);
//          valoracion.setUPC(producto);
            try{
                valoracionEJB.create(valoracion);
            }catch(Exception e){
                System.out.println("Error al insertar el producto "+ e.getMessage());
            }
            
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
