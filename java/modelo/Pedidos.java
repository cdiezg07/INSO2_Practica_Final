/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "pedidos")
public class Pedidos implements Serializable {

    @Id
    @Column(name = "idPedidos")
    private String upc;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha") //del día que se hizo el pedido
    private Date fecha;

    @Column(name = "estado")
    private String estado;

    @Column(name = "incluyeMontaje")
    private String incluyeMontaje;

    @Column(name = "subtotal")
    private float subtotal;

    @Column(name = "numeroTarjeta")
    private String numeroTarjeta;
    @Column(name = "titularTarjeta")
    private String titularTarjeta;
    @Column(name = "CVV")
    private String CVV;
    @Column(name = "caducidad")
    private String caducidad;

    @JoinColumn(name = "metodoEnvio")
    @ManyToOne 
    private Envios envio;

    @JoinColumn(name = "idDireccion")
    @ManyToOne 
    private Direcciones direccion;

    @JoinColumn(name = "emailCliente")
    @ManyToOne
    private Clientes cliente;

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIncluyeMontaje() {
        return incluyeMontaje;
    }

    public void setIncluyeMontaje(String incluyeMontaje) {
        this.incluyeMontaje = incluyeMontaje;
    }

    public Envios getEnvio() {
        return envio;
    }

    public void setEnvio(Envios envio) {
        this.envio = envio;
    }

    public Direcciones getDireccion() {
        return direccion;
    }

    public void setDireccion(Direcciones direccion) {
        this.direccion = direccion;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTitularTarjeta() {
        return titularTarjeta;
    }

    public void setTitularTarjeta(String titularTarjeta) {
        this.titularTarjeta = titularTarjeta;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    
}
