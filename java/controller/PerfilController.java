/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.AdministradoresFacadeLocal;
import EJB.ClientesFacadeLocal;
import EJB.PedidosFacadeLocal;
import EJB.TrabajadoresFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Administradores;
import modelo.Clientes;
import modelo.Pedidos;
import modelo.Trabajadores;
import modelo.Usuarios;

/**
 *
 * @author Mario
 */
@Named
@ViewScoped
public class PerfilController implements Serializable {
    
    private Usuarios usuarioLoggeado; //usuario loggeado que esta en la variable global

    private Clientes attrEspCliente; //vacios si no es cliente

    private Trabajadores attrEspTrabajador; //vacios si no es trabajador            

    private Administradores attrEspAdministrador; //vacios si no es administrador

    private List<Pedidos> pedidosUsuario;
    
    
    boolean esCliente = false;
    boolean esTrabajador = false;
    boolean esAdmin = false;

    //Para interactuar con la base de datos
    @EJB
    private ClientesFacadeLocal clienteEJB;
    @EJB
    private TrabajadoresFacadeLocal trabajadorEJB;
    @EJB
    private AdministradoresFacadeLocal administradorEJB;
    @EJB
    private UsuariosFacadeLocal usuarioEJB;
    @EJB
    private PedidosFacadeLocal pedidosEJB;

    //atributos getter y setter
    public Usuarios getUsuarioLoggeado() {
        return usuarioLoggeado;
    }
    
    public void setUsuarioLoggeado(Usuarios usuarioLoggeado) {
        this.usuarioLoggeado = usuarioLoggeado;
    }
    
    public Clientes getAttrEspCliente() {
        return attrEspCliente;
    }
    
    public void setAttrEspCliente(Clientes attrEspCliente) {
        this.attrEspCliente = attrEspCliente;
    }
    
    public Trabajadores getAttrEspTrabajador() {
        return attrEspTrabajador;
    }
    
    public void setAttrEspTrabajador(Trabajadores attrEspTrabajador) {
        this.attrEspTrabajador = attrEspTrabajador;
    }
    
    public Administradores getAttrEspAdministrador() {
        return attrEspAdministrador;
    }
    
    public void setAttrEspAdministrador(Administradores attrEspAdministrador) {
        this.attrEspAdministrador = attrEspAdministrador;
    }
    
    public boolean isEsCliente() {
        return esCliente;
    }
    
    public void setEsCliente(boolean esCliente) {
        this.esCliente = esCliente;
    }
    
    public boolean isEsTrabajador() {
        return esTrabajador;
    }
    
    public void setEsTrabajador(boolean esTrabajador) {
        this.esTrabajador = esTrabajador;
    }
    
    public boolean isEsAdmin() {
        return esAdmin;
    }
    
    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public List<Pedidos> getPedidosUsuario() {
        return pedidosUsuario;
    }

    public void setPedidosUsuario(List<Pedidos> pedidosUsuario) {
        this.pedidosUsuario = pedidosUsuario;
    }

    
    
    //Una vez el usuario pique en la vista de su perfil se ejecutará la vista y este controlador.
    //PEDIR LOS DATOS DE USUARIO LOGGEADO A LA BASE DE DATOS
    @PostConstruct
    public void init() {
        //para que no de puntero nulo el renderizado condicional de la vista
        this.usuarioLoggeado = new Usuarios();
        this.attrEspAdministrador = new Administradores();
        this.attrEspCliente = new Clientes();
        this.attrEspTrabajador = new Trabajadores();
        
        try {
            //hay que traer el usuario loggeado de la bbdd. Como en la variable global solo tengo el user -> email, pass y el tipo entonces segun el tipo del usuario loggeado traemos unas cosas u otras

            Usuarios usuarioDesfasado = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoggeado");

            //Lo pido a base de datos por si acaso se ha desfasado
            this.usuarioLoggeado = usuarioEJB.find(usuarioDesfasado.getEmail());
            
            if (this.usuarioLoggeado.getTipo().compareTo("cliente") == 0) {

                //llamo a un metodo de la fachada de usuario que le paso el tipo para saber la segunda tabla a consultar y el email
                attrEspCliente = clienteEJB.find(this.usuarioLoggeado.getEmail());
                this.esCliente = true;
                
                //traigo sus pedidos tambien
                this.pedidosUsuario = this.pedidosEJB.findAll();
                
            } else if (this.usuarioLoggeado.getTipo().compareTo("trabajador") == 0) {
                
                attrEspTrabajador = trabajadorEJB.find(this.usuarioLoggeado.getEmail());
                this.esTrabajador = true;
                
            } else {
                
                attrEspAdministrador = administradorEJB.find(this.usuarioLoggeado.getEmail());
                this.esAdmin = true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("ERROR AL CONTACTAR CON LA BBDD");
            
        }
        
    }

    //MODIFICAR LOS DATOS DEL USUARIO LOGGEADO
    public void saveModifiedData() {
        
        try {
            
            if (this.usuarioLoggeado.getTipo().compareTo("cliente") == 0) {

                //por si acaso se ha cambiado tambien lo general de usuario
                this.attrEspCliente.setEmailCliente(this.usuarioLoggeado);

                //actualizamos los atributos especificos
                clienteEJB.edit(attrEspCliente);
                
                addMessage("Correcto", "La información de su cuenta se ha actualizado");
                
            } else {

                //por si acaso se ha cambiado tambien lo general de usuario
                this.attrEspAdministrador.setEmailAdministradores(this.usuarioLoggeado);

                //actualizamos los atributos especificos
                administradorEJB.edit(attrEspAdministrador);
                
                addMessage("Correcto", "La información de su cuenta se ha actualizado");
                
            }
            
        } catch (Exception e) {
            addMessageError("Error", "No ha sido posible actualizar su cuenta");
            
            System.out.println("ERROR AL ACTUALIZAR LOS DATOS");
            
        }
        
    }

    //ELIMINAR LA CUENTA DEL USUARIO LOGGEADO
    public void deleteUserAccount() {
        
        try {

            //Me cargo el objeto de sesion
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLoggeado");
            System.out.println("dEBERIA SER NULL :" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoggeado"));

            //lo borro en la base de datos
            if (this.usuarioLoggeado.getTipo().compareTo("cliente") == 0) {

                //por si acaso se ha cambiado tambien lo general de usuario
                //usuarioEJB.remove(usuarioLoggeado);
                this.attrEspCliente.setEmailCliente(this.usuarioLoggeado);

                //actualizamos los atributos especificos
                clienteEJB.remove(attrEspCliente);
                
                addMessage("Correcto", "Su cuenta se ha eliminado correctamente, ¡esperamos verle de vuelta!");
                
            } else {

                //por si acaso se ha cambiado tambien lo general de usuario
                //usuarioEJB.remove(usuarioLoggeado);
                this.attrEspAdministrador.setEmailAdministradores(this.usuarioLoggeado);

                //actualizamos los atributos especificos
                administradorEJB.remove(attrEspAdministrador);
                
                addMessage("Correcto", "Su cuenta se ha eliminado correctamente, ¡esperamos verle de vuelta!");
                
            }

            //redirige fuera de la sesion              
                 FacesContext.getCurrentInstance().getExternalContext().redirect("../../publico/Principal.xhtml?faces-redirect=true");
            
        } catch (Exception e) {
            addMessageError("Error", "No ha sido posible eliminar su cuenta");
            
            System.out.println("ERROR AL ELIMINAR LA CUENTA");
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Perfil.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addMessageError(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
