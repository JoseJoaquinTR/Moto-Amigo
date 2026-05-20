/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Entrega;
import java.util.List;

/**
 * Interfaz DAO para consultar entregas almacenadas en MongoDB.
 *
 * @author Jesus Omar
 */
public interface IEntregasDAO {

    /**
     * Obtiene todas las entregas registradas.
     *
     * @return lista de entregas registradas.
     * @throws PersistenciaException si ocurre un error al consultar MongoDB.
     */
    List<Entrega> obtenerTodasLasEntregas() throws PersistenciaException;

    /**
     * Obtiene las entregas asignadas a un repartidor.
     *
     * @param idRepartidor identificador del repartidor en formato String/ObjectId.
     * @return lista de entregas del repartidor.
     * @throws PersistenciaException si ocurre un error al consultar MongoDB.
     */
    List<Entrega> obtenerEntregasRepartidor(String idRepartidor) throws PersistenciaException;

    /**
     * Obtiene las entregas asociadas a un emprendedor.
     *
     * @param idEmprendedor identificador del emprendedor en formato String/ObjectId.
     * @return lista de entregas del emprendedor.
     * @throws PersistenciaException si ocurre un error al consultar MongoDB.
     */
    List<Entrega> obtenerEntregasEmprendedor(String idEmprendedor) throws PersistenciaException;
    /**
     * Obtiene las entregas con tipo "DISPONIBLE".
     *
     * @return lista de entregas disponibles.
     * @throws PersistenciaException si ocurre un error al consultar MongoDB.
     */
    List<Entrega> obtenerEntregasDisponibles() throws PersistenciaException;
    
    /**
     * Persiste una entregs asociada a un emprendedor.
     *
     * @param entrega Entrega a persistir
     * @return Entrega persistida
     * @throws PersistenciaException si ocurre un error al consultar MongoDB.
     */
    
    Entrega agregar(Entrega entrega) throws PersistenciaException ;
    
    
}
