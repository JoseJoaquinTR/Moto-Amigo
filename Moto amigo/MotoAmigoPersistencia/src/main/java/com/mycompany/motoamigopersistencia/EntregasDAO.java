/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Entrega;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public class EntregasDAO implements IEntregasDAO{

    private static EntregasDAO instancia;
    
    private EntregasDAO(){
        
    }
    
    public static EntregasDAO getInstancia(){
        if (instancia == null) {
            instancia = new EntregasDAO();
        }
        return instancia;
    }
    
    @Override
    public List<Entrega> obtenerEntregasRepartidor(Long id) {
        List<Entrega> entregasRepartidor = new ArrayList<>();
        List<Entrega> entregas = obtenerTodasLasEntregas();
        for(Entrega e : entregas){
            if(e.getIdRepartidor().equals(id)){
                entregasRepartidor.add(e);
            }
        }
        return entregasRepartidor;
    }

    @Override
    public List<Entrega> obtenerTodasLasEntregas() {
        List<Entrega> listaEntregas = new ArrayList<>();
        listaEntregas.add(new Entrega(1L, 1L, 1L, "Cananea 456", "Norte 322", "Caja", "En camino", 0, 45));
        listaEntregas.add(new Entrega(2L, 1L, 1L, "Amberes 111", "Antonio Ocaso 782", "Caja", "En camino", 0, 10));
        listaEntregas.add(new Entrega(3L, 1L, 1L, "Morelos 834", "Michoacan 567", "Caja", "En camino", 0, 100));
        return listaEntregas;
    }

    @Override
    public List<Entrega> obtenerEntregasEmprendedor(Long id) {
        List<Entrega> entregasEmprendedor = new ArrayList<>();
        List<Entrega> entregas = obtenerTodasLasEntregas();
        for(Entrega e : entregas){
            if(e.getIdEmprendedor().equals(id)){
                entregasEmprendedor.add(e);
            }
        }
        return entregasEmprendedor;
    }
    
}
