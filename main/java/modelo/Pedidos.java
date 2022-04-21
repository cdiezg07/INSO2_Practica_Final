/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author carlos
 */

@Entity
@Table (name="pedidos")
public class Pedidos {
    
    @Id
    @Column(name="idPedidos")
    private String upc;
 
    @Temporal(TemporalType.DATE)
    @Column(name="fecha")
    private Date fecha;

    @Column(name="estado")
    private String estado;
    
    @Column(name="incluyeMontaje")
    private String incluyeMontaje;
    
    @JoinColumn(name="metodoEnvio")
    @OneToMany
    private Envios envio;
    
    @JoinColumn(name="idDireccion")
    @OneToMany
    private Direcciones direccion;
    
    @JoinColumn(name="emailCliente")
    @OneToMany
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

   
    
}
