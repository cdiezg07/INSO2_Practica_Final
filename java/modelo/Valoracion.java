/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "valoracion")
public class Valoracion implements Serializable{

    @Id
    @JoinColumn(name="UPC")
    @ManyToOne//Muchas valoraciones pueden ser dejadas de un producto
    private Products UPC;
    
    @Id
    @JoinColumn(name="emailCliente")
    @ManyToOne//Muchas valoraciones pueden ser dejadas por un cliente
    private Clientes emailCliente;
    
    @Column(name = "estrellas")
    private int estrellas;

    public Products getUPC() {
        return UPC;
    }

    public void setUPC(Products UPC) {
        this.UPC = UPC;
    }

    public Clientes getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(Clientes emailCliente) {
        this.emailCliente = emailCliente;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    
    
}
