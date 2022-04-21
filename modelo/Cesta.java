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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "cesta")
public class Cesta {

    @Id
    @JoinColumn(name="emailCliente")
    @OneToOne //Una cesta solo puede ser tenida por un cliente
    private String emailUsuario;
    @Id
    @JoinColumn(name="UPC")
    @ManyToMany//Una cesta puede contener varios productos y muchas cestas pueden contener un producto
    private String UPC;

    @Column(name = "Cantidad")
    private int Cantidad;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    
    
}
