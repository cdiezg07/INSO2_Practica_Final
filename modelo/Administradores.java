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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "administradores")
public class Administradores {

    @Id
    @JoinColumn(name="email")
      @OneToOne
    private String emailAdministradores;

    @Column(name = "DNI")
    private String DNI;

    public String getEmailAdministradores() {
        return emailAdministradores;
    }

    public void setEmailAdministradores(String emailAdministradores) {
        this.emailAdministradores = emailAdministradores;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    

}
