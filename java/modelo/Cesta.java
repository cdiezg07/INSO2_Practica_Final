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
public class Cesta implements Serializable{

    @Id
    @JoinColumn(name="emailCliente")
    @OneToOne //Una cesta solo puede ser tenida por un cliente
    private Clientes emailUsuario;
    
    @Id
    @JoinColumn(name="UPC")
    @ManyToMany//Una cesta puede contener varios productos y muchas cestas pueden contener un producto
    private List<Products> UPC;

    @Column(name = "Cantidad")
    private int Cantidad;

    public Clientes getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(Clientes emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public List<Products> getUPC() {
        return UPC;
    }

    public void setUPC(List<Products> UPC) {
        this.UPC = UPC;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    
    
}
