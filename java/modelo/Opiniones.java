/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mario
 */
@Entity
@Table (name="opiniones")
public class Opiniones implements Serializable{

    @Id
    @JoinColumn(name="UPC")
    @ManyToOne//muchas opciniones pueden ser de un producto
    private Products UPC;
    
    @Id
    @JoinColumn(name="emailCliente")
    @ManyToOne//Muchas opiniones pueden ser de un cliente
    private Clientes emailCliente;
    
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "puntuacion")
    private int puntuacion;

    @Column(name = "comentario")
    private String comentario;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
