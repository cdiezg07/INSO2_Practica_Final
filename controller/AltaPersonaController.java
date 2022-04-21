/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PersonasFacadeLocal;
import EJB.UsuariosFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Personas;
import modelo.Usuarios;

/**
 *
 * @author carlos
 */
@Named
@ViewScoped
public class AltaPersonaController implements Serializable{
    
    private Personas persona;
    
    @EJB
    private PersonasFacadeLocal personaEJB;
    
    @PostConstruct
    public void init(){
        persona = new Personas();
    }
        
    public void insertarPersona(){
        try{
            personaEJB.create(persona);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+ e.getMessage());
        }
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
    
    
}
