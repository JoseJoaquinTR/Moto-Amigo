package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.RepartidorDTO;

/**
 * Operaciones de negocio relacionadas con repartidores.
 *
 * @author joset
 */
public interface IRepartidorBO {

    /**
     * Obtiene los datos de un repartidor a partir de su identificador.
     *
     * @param idRepartidor identificador del repartidor.
     * @return datos del repartidor; null si no se encuentra.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    RepartidorDTO obtenerRepartidorPorId(Long idRepartidor) throws NegocioException;
}
