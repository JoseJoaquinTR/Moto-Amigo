package com.mycompany.curepartidor;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Caso de uso para buscar un repartidor por su identificador.
 *
 * @author Carmen Andrea Lara Osuna
 */
public interface IBuscarRepartidorPorId {

    /**
     * Obtiene los datos de un repartidor a partir de su identificador.
     *
     * @param id identificador del repartidor.
     * @return datos del repartidor; null si no se encuentra.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    RepartidorDTO buscarRepartidorPorId(String id) throws NegocioException;
}
