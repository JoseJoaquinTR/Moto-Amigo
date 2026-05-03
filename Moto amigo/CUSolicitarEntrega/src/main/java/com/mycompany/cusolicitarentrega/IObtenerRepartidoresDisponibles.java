package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 * Caso de uso para obtener los repartidores disponibles del sistema.
 *
 * @author Carmen Andrea Lara Osuna
 */
public interface IObtenerRepartidoresDisponibles {

    /**
     * Obtiene la lista de repartidores actualmente disponibles.
     *
     * @return lista de repartidores disponibles.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<RepartidorDTO> obtenerRepartidoresDisponibles() throws NegocioException;
}
