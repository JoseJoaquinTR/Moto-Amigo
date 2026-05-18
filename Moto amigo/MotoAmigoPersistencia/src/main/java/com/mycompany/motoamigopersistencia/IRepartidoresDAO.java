/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import enums.*;
import com.mycompany.Entidades.Repartidor;
import java.util.List;

/**
 *
 * @author Carmen
 */
public interface IRepartidoresDAO {

    List<Repartidor> obtenerActivos() throws PersistenciaException;

    Repartidor cambiarEstado( String id, Estado estado)throws PersistenciaException;

    List<Repartidor> consultarTodos()throws PersistenciaException;

    List<Repartidor> buscarPorNombre(String nombreParcial)throws PersistenciaException;
    
    Repartidor consultarPorId(String id)throws PersistenciaException;
    
    Repartidor incrementarNumeroBloqueos(String id) throws PersistenciaException;
}
