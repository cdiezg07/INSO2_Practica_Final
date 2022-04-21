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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author carlos
 */
@Entity
@Table (name="envios")
public class Envios implements Serializable{
    
    @Id
    @Column(name="metodoEnvio")
    private String metodoEnvio;
 
    @Column(name="gastos")
    private float gastos;

    @Column(name="fechaDestino")
    private String fechaDestino;
    
    @Column(name="empresa")
    private String empresa;

    
    
}
