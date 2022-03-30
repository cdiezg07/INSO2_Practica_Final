/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author carlos
 */

@Entity
@Table (name="menus")
public class Menus {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idMenu;
 
    @Column(name="Nombre")
    private String nombre;
 
    @Column(name="Tipo")
    private int tipo;
    
    @Column(name="Estado")
    private String estado;
    
    @Column(name="url")
    private String url;
    
    @JoinColumn(name="IdMenu_Menu")
    @ManyToOne
    private Menus menu;
    
    @JoinColumn(name="IdRol")
    @ManyToOne
    private Roles rol;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
    
    
}
