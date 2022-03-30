/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author carlos
 */

@Entity
@Table (name="usuarios")
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idUsuario;
 
    @Column(name="User")
    private String user;
 
    @Column(name="Password")
    private String password;
    
    @Column(name="UltimaConexion")
    private Date ultimaConexion;
    
    @Column(name="Estado")
    private String estado;
    
    @JoinColumn(name="IdPersona")
    @OneToOne
    private Personas persona;
    
    @JoinColumn(name="IdRol")
    @ManyToOne
    private Roles rol;

    
}
