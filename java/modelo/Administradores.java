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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "administradores")
public class Administradores implements Serializable {

    @Id
    @JoinColumn(name = "emailAdministradores")
    @OneToOne
    private Usuarios emailAdministradores;

    @Column(name = "DNI")
    private String DNI;

    public Usuarios getEmailAdministradores() {
        return emailAdministradores;
    }

    public void setEmailAdministradores(Usuarios emailAdministradores) {
        this.emailAdministradores = emailAdministradores;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

}
