/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Repartidor;
import java.util.List;

/**
 *
 * @author xiomi
 */
public interface IRepartidorDAO {
    
    List <Repartidor> obtenerRepartidoresDisponibles();
    Repartidor obtenerRepartidorPorId(Long idRepartidor);
}
