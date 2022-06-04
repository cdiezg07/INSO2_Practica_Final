/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CestaFacadeLocal;
import EJB.ClientesFacadeLocal;
import EJB.DireccionesFacadeLocal;
import EJB.EnviosFacadeLocal;
import EJB.PedidosFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Cesta;
import modelo.Clientes;
import modelo.Direcciones;
import modelo.Envios;
import modelo.Pedidos;
import modelo.Usuarios;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mario
 */
@Named
@ViewScoped
public class CestaController implements Serializable {

    private Usuarios usuarioActivo;
    private Clientes infoEspCliente;
    private List<Cesta> cestaUsuarioActivo;
    private List<Direcciones> direccionesUsuario;
    private float subtotal;
    private Direcciones direccionSeleccionada;
    private Direcciones nuevaDireccion;
    private String montaje;
    private List<Envios> enviosDisponibles;
    private String envioSeleccionado;
    private Pedidos pedido;

    @EJB
    private CestaFacadeLocal cestaEJB;
    @EJB
    private ClientesFacadeLocal clientesEJB;
    @EJB
    private DireccionesFacadeLocal direccionesEJB;
    @EJB
    private EnviosFacadeLocal enviosEJB;
    @EJB
    private PedidosFacadeLocal pedidosEJB;

    public List<Cesta> getCestaUsuarioActivo() {
        return cestaUsuarioActivo;
    }

    public void setCestaUsuarioActivo(List<Cesta> cestaUsuarioActivo) {
        this.cestaUsuarioActivo = cestaUsuarioActivo;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public List<Direcciones> getDireccionesUsuario() {
        return direccionesUsuario;
    }

    public void setDireccionesUsuario(List<Direcciones> direccionesUsuario) {
        this.direccionesUsuario = direccionesUsuario;
    }

    public Direcciones getNuevaDireccion() {
        return nuevaDireccion;
    }

    public void setNuevaDireccion(Direcciones nuevaDireccion) {
        this.nuevaDireccion = nuevaDireccion;
    }

    public String getEnvioSeleccionado() {
        return envioSeleccionado;
    }

    public void setEnvioSeleccionado(String envioSeleccionado) {
        this.envioSeleccionado = envioSeleccionado;
    }

    public List<Envios> getEnviosDisponibles() {
        return enviosDisponibles;
    }

    public void setEnviosDisponibles(List<Envios> enviosDisponibles) {
        this.enviosDisponibles = enviosDisponibles;
    }

    public String getMontaje() {
        return montaje;
    }

    public void setMontaje(String montaje) {
        this.montaje = montaje;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    @PostConstruct
    public void init() {

        this.subtotal = 0;
        this.nuevaDireccion = new Direcciones();
        this.enviosDisponibles = new ArrayList<>();
        this.pedido = new Pedidos();

        //Según el usuario que esté loggeado se trae lo que tenga en la cesta
        this.usuarioActivo = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoggeado");
        this.infoEspCliente = this.clientesEJB.find(this.usuarioActivo.getEmail());

        this.cestaUsuarioActivo = this.cestaEJB.getProductos(this.infoEspCliente);

        if (this.cestaUsuarioActivo != null) {

            this.calcularSubtotal();

        }

        //traemos las direcciones del usuario
        this.direccionesUsuario = this.direccionesEJB.traerTodas(this.infoEspCliente);

        //traemos los envios disponibles
        this.enviosDisponibles = this.enviosEJB.findAll();

    }

    public void calcularSubtotal() {

        float val = 0;

        for (int i = 0; i < this.cestaUsuarioActivo.size(); i++) {
            val = val + (this.cestaUsuarioActivo.get(i).getUPC().getPriceNumeral() * this.cestaUsuarioActivo.get(i).getCantidad());

        }

        this.subtotal = val;
    }

    public void eliminarDeCesta(Cesta cestaABorrar) {
        //se elimina de la cesta de la base de datos y de la lista local
        cestaEJB.remove(cestaABorrar);
        this.cestaUsuarioActivo.remove(cestaABorrar);
        this.subtotal = 0;
    }

    public void seleccionDireccion(Direcciones direccion) {
        this.direccionSeleccionada = direccion;
    }

    public void eliminarDireccion(Direcciones direccion) {

        try {
            this.direccionesEJB.remove(direccion);
            this.direccionesUsuario.remove(direccion);

        } catch (Exception e) {

            System.out.println("ERROR AL CONTACTAR CON LA BBDD" + e.getMessage());

        }

    }

    public void guardarDireccion() {

        Random rand = new Random(); //instance of random class
        int upperbound = 19999;
        int int_random = rand.nextInt(upperbound);
        int numAleatorio = int_random;

        this.nuevaDireccion.setIdDirecciones(numAleatorio);
        this.nuevaDireccion.setEmailUsuario(this.infoEspCliente);
        this.direccionesUsuario.add(this.nuevaDireccion);
        try {
            this.direccionesEJB.create(this.nuevaDireccion);
        } catch (Exception e) {

            System.out.println("ERROR AL CONTACTAR CON LA BBDD" + e.getMessage());

        }
    }

    public void tramitarPedido() {

        Random rand = new Random(); //instance of random class
        int upperbound = 19999;
        int int_random = rand.nextInt(upperbound);
        int numAleatorio = int_random;

        this.pedido.setUpc(this.usuarioActivo.getNombre() + numAleatorio);
        Date today = new Date();
        today.setHours(0);
        this.pedido.setFecha(today);
        
        

        
        

        this.pedido.setEstado("Procesandose");

//Hay que sacar la referencia del objeto envio para meterla
        for (int i = 0; i < this.enviosDisponibles.size(); i++) {
            if (this.enviosDisponibles.get(i).getMetodoEnvio().compareTo(this.envioSeleccionado) == 0) {
                this.pedido.setEnvio(this.enviosDisponibles.get(i));
            }
        }
        
        float costeMontaje = 0;
        
        if(this.montaje.compareTo("Si") == 0){
            costeMontaje = 25;
        }
        
        
        float subtotalFinal = this.subtotal + this.pedido.getEnvio().getGastos()+costeMontaje; 
        
        this.pedido.setSubtotal(subtotalFinal);
        

        this.pedido.setDireccion(this.direccionSeleccionada);
        this.pedido.setCliente(this.infoEspCliente);

        this.pedido.setIncluyeMontaje(this.montaje);

        try {
            this.pedidosEJB.create(this.pedido);
            
            //y se borra la cesta porque ya se ha hecho efectivo
            this.cestaEJB.eliminarCesta(this.infoEspCliente);
                    
            FacesContext.getCurrentInstance().getExternalContext().redirect("./Perfil.xhtml?faces-redirect=true");

        } catch (Exception e) {

            System.out.println("ERROR AL CONTACTAR CON LA BBDD" + e.getMessage());

        }


    }

}
