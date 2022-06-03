/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "clientes")
public class Clientes implements Serializable {

    @Id
    @JoinColumn(name = "emailCliente")
    @OneToOne (cascade = CascadeType.ALL)
    private Usuarios emailCliente;

    @Column(name = "numero_telefono")
    private String numero_telefono;//

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "direccion")
    private String direccion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    public Usuarios getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(Usuarios emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    public Clientes find(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
