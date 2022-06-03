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
import javax.inject.Inject;
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
public class SubcategoriasController implements Serializable {

    private Subcategorias subcat;
    private List<Subcategorias> listaSubcat;
    private Clientes cliente;
    
    @Inject
    private PrincipalController vistaAnteriorCongelada;

    @EJB
    private SubcategoriasFacadeLocal subcatEJB;
    @EJB
    private ProductsFacadeLocal productsEJB;
    @EJB
    private CestaFacadeLocal cestaEJB;
    @EJB
    private ClientesFacadeLocal clienteEJB;

    private List<Products> listaProductosSubcategoria;
    private Categorias categoriaAnterior;
    private Products producto;
    private List<Products> listaProductos;
    private Cesta cesta;
    
    @PostConstruct
    public void init() {
        subcat = new Subcategorias();
        producto = new Products();
        cesta = new Cesta();
        cliente = new Clientes();
        categoriaAnterior = this.vistaAnteriorCongelada.getActualCategoria();
        System.out.println("lfsjd"+this.vistaAnteriorCongelada);
        System.out.println("categoriaAnterior"+this.categoriaAnterior.getNombre());
        List<String> listaCategoria = getSubcategorias(this.categoriaAnterior.getNombre());
        listaProductosSubcategoria = getAllProductsFromSubcat(listaCategoria);
    }

    public void insertarSubcategoria() {
        try {
            subcatEJB.create(subcat);
        } catch (Exception e) {
            System.out.println("Error al insertar la categoria " + e.getMessage());
        }
    }

    public List<String> getSubcategorias(String nombreCat) {

        try {
            listaSubcat = subcatEJB.findAll();
            List<String> listaFinal = new ArrayList<String>();
            System.out.println("fsdfasdfasdf" + nombreCat);
            for (Subcategorias sub : listaSubcat) {
//                System.out.println(sub.getCategoria().getNombre());
                if (sub.getCategoria().getNombre().equals(nombreCat)) {
                    listaFinal.add(sub.getNombreSubcategoria());
                }
            }
            return listaFinal;

        } catch (Exception e) {
            System.out.println("Error al obtener la subcategoria " + e.getMessage());
        }
        return null;
    }

    public List<Subcategorias> getSubcategorias1(String nombreCat) {

        try {
            listaSubcat = subcatEJB.findAll();
            List<Subcategorias> listaFinal = new ArrayList<Subcategorias>();
            System.out.println("fsdfasdfasdf" + nombreCat);
            for (Subcategorias sub : listaSubcat) {
//                System.out.println(sub.getCategoria().getNombre());
                if (sub.getCategoria().getNombre().equals(nombreCat)) {
                    listaFinal.add(sub);
                }
            }
            return listaFinal;

        } catch (Exception e) {
            System.out.println("Error al obtener la subcategoria " + e.getMessage());
        }
        return null;
    }
    
    public List<Products> getProducts(String nombreSubCategoria) {

        try {
            listaProductos = productsEJB.findAll();
            List<Products> listaFinal = new ArrayList<Products>();
            System.out.println("fsdfasdfasdf" + nombreSubCategoria);
            for (Products pro : listaProductos) {
//                System.out.println(pro.getSubcategoria().getNombreSubcategoria());
                if (pro.getSubcategoria().getNombreSubcategoria().equals(nombreSubCategoria)) {
                    listaFinal.add(pro);
                }
            }
            this.listaProductosSubcategoria = listaFinal;
            this.init();
            return listaFinal;
            
        } catch (Exception e) {
            System.out.println("Error al obtener los productos " + e.getMessage());
        }
        return null;
    }
    
    public List<Products> getAllProductsFromSubcat(List<String> subcat) {

        try {
            listaProductos = productsEJB.findAll();
            List<Products> listaFinal = new ArrayList<Products>();
            System.out.println("fsdfasdfasdf" + subcat.size());
            for (Products pro : listaProductos) {
                if (subcat.contains(pro.getSubcategoria().getNombreSubcategoria())) {
                    listaFinal.add(pro);
                }
            }
            return listaFinal;
            
        } catch (Exception e) {
            System.out.println("Error al obtener los productos de las subcategorias" + e.getMessage());
        }
        return null;
    }
    
    public Products getProduct(String UPC) {

        try {
            producto = productsEJB.find(UPC);

        } catch (Exception e) {
            System.out.println("Error al obtener el producto " + e.getMessage());
        }
        return producto;
    }

    public void insertarProductsCesta(String UPC, String emailCliente) {
        try {
            cliente = clienteEJB.find(emailCliente);
            producto = getProduct(UPC);

            if (!cestaEJB.yaExisteProducto(producto, cliente)) {
                cesta.setUPC(producto);
                cesta.setEmailUsuario(cliente);
                System.out.println("La cesta es");
                System.out.println(cesta.getUPC());
                System.out.println(cesta.getEmailUsuario());
                cesta.setCantidad(3);
                cestaEJB.create(cesta);
            } else {
                System.out.println("Ya esta en la cesta");
            }

        } catch (Exception e) {
            System.out.println("Error al insertar los productos en la cesta " + e.getMessage());
        }
    }

//    public Categorias getCat() {
//        return cat;
//    }
//
//    public void setCat(Categorias cat) {
//        this.cat = cat;
//    }   

    public PrincipalController getVistaAnteriorCongelada() {
        return vistaAnteriorCongelada;
    }

    public void setVistaAnteriorCongelada(PrincipalController vistaAnteriorCongelada) {
        this.vistaAnteriorCongelada = vistaAnteriorCongelada;
    }

    public Categorias getCategoriaAnterior() {
        return categoriaAnterior;
    }

    public void setCategoriaAnterior(Categorias categoriaAnterior) {
        this.categoriaAnterior = categoriaAnterior;
    }

    public List<Products> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Products> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Products> getListaProductosSubcategoria() {
        return listaProductosSubcategoria;
    }

    public void setListaProductosSubcategoria(List<Products> listaProductosSubcategoria) {
        this.listaProductosSubcategoria = listaProductosSubcategoria;
    }
    
    
}
