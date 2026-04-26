/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.motoamigodto.RepartidorDTO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author xiomi
 */
public class RepartidorDAO implements IRepartidorDAO {
    private static RepartidorDAO instancia;

    public RepartidorDAO() {
    }
    
    public static RepartidorDAO getInstance() {
        if (instancia == null) {
            instancia = new RepartidorDAO();
        }
        return instancia;
    }

    
    @Override
    public List<RepartidorDTO> obtenerRepartidoresDisponibles() {
        List<RepartidorDTO> repartidores = new ArrayList<>();
        repartidores.add(new RepartidorDTO(1, "Carlos Pérez", "6441234567", "carlos@mail.com", "Moto", "disponible"));
        repartidores.add(new RepartidorDTO(2, "Ana López", "6449876543", "ana@mail.com", "Bicicleta", "disponible"));
        repartidores.add(new RepartidorDTO(3, "Luis Ramírez", "6445556677", "luis@mail.com", "Moto", "disponible"));
        return repartidores;
    }
    
}
