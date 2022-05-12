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
@Table(name = "trabajadores")
public class Trabajadores implements Serializable{

    @Id
    @JoinColumn(name="emailTrabajador")
    @OneToOne
    private Usuarios emailTrabajador;

    @Column(name = "dni")
    private String DNI;

    @Column(name = "num_telefono")
    private String num_telefono;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    public Usuarios getEmailTrabajador() {
        return emailTrabajador;
    }

    public void setEmailTrabajador(Usuarios emailTrabajador) {
        this.emailTrabajador = emailTrabajador;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNum_telefono() {
        return num_telefono;
    }

    public void setNum_telefono(String num_telefono) {
        this.num_telefono = num_telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    
}
