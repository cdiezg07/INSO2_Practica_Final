/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.AdministradoresFacadeLocal;
import EJB.ClientesFacadeLocal;
import EJB.TrabajadoresFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Administradores;
import modelo.Clientes;
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

    //Para interactuar con la base de datos
    @EJB
    private ClientesFacadeLocal clienteEJB;
    @EJB
    private TrabajadoresFacadeLocal trabajadorEJB;
    @EJB
    private AdministradoresFacadeLocal administradorEJB;
    @EJB
    private UsuariosFacadeLocal usuarioEJB;

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

            } else if (this.usuarioLoggeado.getTipo().compareTo("trabajador") == 0) {

                attrEspTrabajador = trabajadorEJB.find(this.usuarioLoggeado.getEmail());

            } else {

                attrEspAdministrador = administradorEJB.find(this.usuarioLoggeado.getEmail());

            }

        } catch (Exception e) {

            System.out.println("ERROR AL CONTACTAR CON LA BBDD");

        }

        // System.out.println(this.usuarioLoggeado.getEmail());
        //   System.out.println(this.usuarioLoggeado.getNombre());
        //  System.out.println(this.usuarioLoggeado.getApellidos());
        //  System.out.println(this.usuarioLoggeado.getPassword());
        //  System.out.println(this.usuarioLoggeado.getTipo());
        // System.out.println(this.attrEspAdministrador.getEmailAdministradores());
        // System.out.println(this.attrEspAdministrador.getDNI());
        // System.out.println(this.attrEspCliente.getEmailCliente());
        // System.out.println(this.attrEspCliente.getFecha_nacimiento());
        // System.out.println(this.attrEspCliente.getNumero_telefono());
        //  System.out.println(this.attrEspCliente.getDireccion());
        //  System.out.println(this.attrEspCliente.getProvincia());
        //  System.out.println(this.attrEspCliente.getMunicipio());
        //  System.out.println(this.attrEspTrabajador.getEmailTrabajador());
        //   System.out.println(this.attrEspTrabajador.getDNI());
        // System.out.println(this.attrEspTrabajador.getNum_telefono());
        //  System.out.println(this.attrEspTrabajador.getFecha_nacimiento());
        // this.usuarioLoggeado.setPassword("0000");
        // this.usuarioLoggeado.setNombre("0000");
        // this.usuarioLoggeado.setApellidos("0000");
        // this.attrEspAdministrador.setDNI("0000000");
        // this.saveModifiedData();
        // System.out.println(this.deleteUserAccount());
    }

    //MODIFICAR LOS DATOS DEL USUARIO LOGGEADO
    public void saveModifiedData() {

        try {

            if (this.usuarioLoggeado.getTipo().compareTo("cliente") == 0) {

                //por si acaso se ha cambiado tambien lo general de usuario
                usuarioEJB.edit(usuarioLoggeado);

                //actualizamos los atributos especificos
                clienteEJB.edit(attrEspCliente);

            } else if (this.usuarioLoggeado.getTipo().compareTo("trabajador") == 0) {

                //por si acaso se ha cambiado tambien lo general de usuario
                usuarioEJB.edit(usuarioLoggeado);

                //actualizamos los atributos especificos
                trabajadorEJB.edit(attrEspTrabajador);

            } else {

                //por si acaso se ha cambiado tambien lo general de usuario
                usuarioEJB.edit(usuarioLoggeado);

                //actualizamos los atributos especificos
                administradorEJB.edit(attrEspAdministrador);

            }

        } catch (Exception e) {

            System.out.println("ERROR AL ACTUALIZAR LOS DATOS");

        }

    }

    //ELIMINAR LA CUENTA DEL USUARIO LOGGEADO
    public String deleteUserAccount() {

        try {

            //Me cargo el objeto de sesion
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLoggeado");
            System.out.println("dEBERIA SER NULL :" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoggeado"));

            //lo borro en la base de datos
            if (this.usuarioLoggeado.getTipo().compareTo("cliente") == 0) {

                //por si acaso se ha cambiado tambien lo general de usuario
                usuarioEJB.remove(usuarioLoggeado);

                //actualizamos los atributos especificos
                clienteEJB.remove(attrEspCliente);

            } else if (this.usuarioLoggeado.getTipo().compareTo("trabajador") == 0) {

                //por si acaso se ha cambiado tambien lo general de usuario
                usuarioEJB.remove(usuarioLoggeado);

                //actualizamos los atributos especificos
                trabajadorEJB.remove(attrEspTrabajador);

            } else {

                //por si acaso se ha cambiado tambien lo general de usuario
                usuarioEJB.remove(usuarioLoggeado);

                //actualizamos los atributos especificos
                administradorEJB.remove(attrEspAdministrador);

            }

            //redirige fuera de la sesion              
            return "principal.xhtml";

        } catch (Exception e) {

            System.out.println("ERROR AL ELIMINAR LA CUENTA");
            return "./";

        }
    }

}
