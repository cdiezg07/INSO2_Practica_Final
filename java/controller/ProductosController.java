/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CestaFacadeLocal;
import EJB.ClientesFacadeLocal;
import EJB.OpinionesFacadeLocal;
import EJB.ProductsFacadeLocal;
import EJB.SubcategoriasFacadeLocal;
import EJB.ValoracionFacadeLocal;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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
@RequestScoped
public class ProductosController implements Serializable {

    private Products producto;
    private List<Products> listaProductos;

    @Inject
    private SubcategoriasController vistaAnterior;

    @EJB
    private ProductsFacadeLocal productsEJB;

    @EJB
    private OpinionesFacadeLocal opinionesEJB;
    @EJB
    private CestaFacadeLocal cestaEJB;
    @EJB
    private ClientesFacadeLocal clienteEJB;
    @EJB
    private ValoracionFacadeLocal valoracionEJB;
    private Valoracion valoracion;
    private Opiniones opinion;
    private Clientes cliente;
    private Usuarios usu;
    private Products productoSeleccionado;
    private boolean enElCarrito;
    private String nuevaResenya;
    private int rating;

    @PostConstruct
    public void init() {

        producto = new Products();
        usu = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoggeado");
        //this.productoSeleccionado = vistaAnterior.getActualProductos();

        this.productoSeleccionado = this.productsEJB.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("produtoSeleccionado"));
        System.out.println("vista anterior congelada: " + this.vistaAnterior);
        System.out.println("categoriaAnterior: " + this.productoSeleccionado.getName());

        if (usu != null) {
            this.enElCarrito = this.cestaEJB.yaExisteProducto(this.productoSeleccionado, clienteEJB.find(usu.getEmail()));
        } else {
            this.enElCarrito = false;
        }

    }

    public void insertarSubcategoria() {
        try {
            productsEJB.create(producto);
        } catch (Exception e) {
            System.out.println("Error al insertar el producto " + e.getMessage());
        }
    }

    public List<Products> getProducts(String nombreSubCategoria) {

        try {
            listaProductos = productsEJB.findAll();
            List<Products> listaFinal = new ArrayList<Products>();
            System.out.println("fsdfasdfasdf" + nombreSubCategoria);
            for (Products pro : listaProductos) {
                System.out.println(pro.getSubcategoria().getNombreSubcategoria());
                if (pro.getSubcategoria().getNombreSubcategoria().equals(nombreSubCategoria)) {
                    listaFinal.add(pro);
                }
            }
            return listaFinal;

        } catch (Exception e) {
            System.out.println("Error al obtener el producto " + e.getMessage());
        }
        return null;
    }

   
    public void crearValoracion() {
        Products producto;
        if (usu.getEmail() == null) {
            System.out.println("No hay usuario logueado");
        } else {
            cliente = clienteEJB.find(usu.getEmail());
            valoracion.setEmailCliente(cliente);
            valoracion.setEstrellas(3);
//          valoracion.setUPC(producto);
            try {
                valoracionEJB.create(valoracion);
            } catch (Exception e) {
                System.out.println("Error al insertar el producto " + e.getMessage());
            }

        }

    }

    public void addCarrito(Products producto) {
        if (usu == null) {
            addMessageError(FacesMessage.SEVERITY_ERROR, "Error", "Debe iniciar sesión para poder añadir productos a su carrito");

        } else {
            System.out.println("Usuario: " + usu);

            Cesta itemCesta = new Cesta();
            itemCesta.setUPC(this.productoSeleccionado);
            itemCesta.setCantidad(1);
            Clientes cli = clienteEJB.find(usu.getEmail());

            itemCesta.setEmailUsuario(cli);
            try {
                this.cestaEJB.create(itemCesta);
                this.enElCarrito = true;
            } catch (Exception e) {
                System.out.println("ERROR al insertar el producto en la cesta");
            }
        }

    }

    public List<Opiniones> getOpiniones(Products producto) {
        List<Opiniones> opinion = productsEJB.getOpiniones(producto);
      

        return opinion;
    }

    public void nuevaResenya() {

        Opiniones nuevaOpinion = new Opiniones();
        if (usu == null) {
            System.out.println("No hay usuario logueado");
            addMessageError(FacesMessage.SEVERITY_ERROR, "Error", "Debe iniciar sesión para poder comentar");

        } else {
            nuevaOpinion.setUPC(this.productoSeleccionado);
            nuevaOpinion.setEmailCliente(clienteEJB.find(usu.getEmail()));
            Date today = new Date();
            today.setHours(2);
            nuevaOpinion.setFecha(today);
            nuevaOpinion.setComentario(this.nuevaResenya);
            nuevaOpinion.setPuntuacion(this.rating);

            try {

                opinionesEJB.create(nuevaOpinion);
                this.nuevaResenya = "";
            } catch (Exception e) {
                addMessageError(FacesMessage.SEVERITY_INFO, "Info Message", "Ya has creado una reseña. Vuelve a crearla en las próximas 24 horas");
                System.out.println("Error al insertar el producto " + e.getMessage());
            }
        }

    }

    public Usuarios getUsu() {
        return usu;
    }

    public void setUsu(Usuarios usu) {
        this.usu = usu;
    }

    public Products getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Products productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public boolean isEnElCarrito() {
        return enElCarrito;
    }

    public void setEnElCarrito(boolean enElCarrito) {
        this.enElCarrito = enElCarrito;
    }

    public String getNuevaResenya() {
        return nuevaResenya;
    }

    public void setNuevaResenya(String nuevaResenya) {
        this.nuevaResenya = nuevaResenya;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public boolean esCliente(){
        if(usu == null){
            return false;
        }else if(usu.getTipo().equals("cliente")){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean esTrabajador(){
        if(usu == null){
            return false;
        }else if(usu.getTipo().equals("trabajador")){
            return true;
        }else{
            return false;
        }
    }
    
    public void descuento(){
        System.out.println("Descuento");
        try {

            productsEJB.edit(productoSeleccionado);

        } catch (Exception e) {
            System.out.println("Error al modificar el producto " + e.getMessage());
        }
        
    }

  
    public void addMessageError(FacesMessage.Severity severity, String summary, String detail) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<String> completeArea(String query) {
        List<String> results = new ArrayList<>();

        if ("PrimeFaces".equals(query)) {
            results.add("PrimeFaces Rocks!!!");
            results.add("PrimeFaces has 100+ components.");
            results.add("PrimeFaces is lightweight.");
            results.add("PrimeFaces is easy to use.");
            results.add("PrimeFaces is developed with passion!");
        } else {
            for (int i = 0; i < 10; i++) {
                results.add(query + i);
            }
        }

        return results;
    }

}
