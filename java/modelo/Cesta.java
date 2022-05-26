/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "cesta")
public class Cesta implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cestaId")
    private int cestaId;
    
    @JoinColumn(name="emailUsuario")
    @OneToOne //Una cesta solo puede ser tenida por un cliente
    private Clientes emailUsuario;
    
    @JoinColumn(name="UPC")
    @ManyToOne//Una cesta puede contener varios productos y muchas cestas pueden contener un producto
    private Products upc;

    @Column(name = "Cantidad")
    private int Cantidad;

    public Clientes getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(Clientes emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Products getUPC() {
        return upc;
    }

    public void setUPC(Products UPC) {
        this.upc = UPC;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getCestaId() {
        return cestaId;
    }

    public void setCestaId(int cestaId) {
        this.cestaId = cestaId;
    }
    
}
