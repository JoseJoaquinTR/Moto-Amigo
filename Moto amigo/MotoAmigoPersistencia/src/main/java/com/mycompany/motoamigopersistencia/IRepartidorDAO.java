/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Estado;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.FiltrosDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import java.util.List;

/**
 *
 * @author Carmen
 */
public interface IRepartidorDAO {

    List<Repartidor> obtenerActivos() throws PersistenciaException;

    Repartidor cambiarEstado(RepartidorDTO dto, Estado estado)throws PersistenciaException;

    List<Repartidor> consultarTodos()throws PersistenciaException;

    List<Repartidor> buscarPorNombre(String nombreParcial)throws PersistenciaException;
    
    Repartidor consultarPorId(String id)throws PersistenciaException;
}
