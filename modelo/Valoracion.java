/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
public class Valoracion {

    @Id
    @JoinColumn(name="UPC")
    @ManyToOne//Muchas valoraciones pueden ser dejadas de un producto
    private String UPC;
    @Id
    @JoinColumn(name="emailCliente")
    @ManyToOne//Muchas valoraciones pueden ser dejadas por un cliente
    private String emailCliente;
    
    @Column(name = "estrellas")
    private int estrellas;

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    
    
}
