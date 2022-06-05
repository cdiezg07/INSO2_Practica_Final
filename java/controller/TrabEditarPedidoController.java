/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PedidosFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Pedidos;

/**
 *
 * @author Mario
 */
@Named
@ViewScoped
public class TrabEditarPedidoController implements Serializable {

    private List<Pedidos> todosLosPedidos;
    
    @EJB
    private PedidosFacadeLocal pedidosEJB;

    public List<Pedidos> getTodosLosPedidos() {
        return todosLosPedidos;
    }

    public void setTodosLosPedidos(List<Pedidos> todosLosPedidos) {
        this.todosLosPedidos = todosLosPedidos;
    }
    
    
    @PostConstruct
    public void init() {
        
        //nos traemos todos los pedidos 
        this.todosLosPedidos = this.pedidosEJB.findAll();
        
    }
    
    
  

    public void eliminarPedidoSeleccionado(Pedidos pedidoPicado) {

        try {
            this.pedidosEJB.remove(pedidoPicado);

        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR A LA HORA DE ELIMINAR EL PEDIDO");
        }
        this.init();
    }
    

    public void guardarPedidoModificado(Pedidos pedidoModificado) {

        try {
            this.pedidosEJB.edit(pedidoModificado);
        } catch (Exception e) {
            System.out.println("SE HA PRODUCIDO UN ERROR A LA HORA DE GUARDAR EL PEDIDO");
        }
        this.init();
    }
    
    
    
    
    

}
