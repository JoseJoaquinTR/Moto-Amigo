package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.EntregaDTO;
import java.util.List;

/**
 * Operaciones de negocio relacionadas con entregas.
 *
 * @author Jesus Omar
 */
public interface IEntregasBO {

    /**
     * Obtiene la lista de entregas asignadas al repartidor indicado.
     *
     * @param id identificador del repartidor.
     * @return lista de entregas; vacía si no hay entregas.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<EntregaDTO> obtenerEntregasRepartidor(String id) throws NegocioException;

    /**
     * Obtiene la lista de entregas asociadas al emprendedor indicado.
     *
     * @param id identificador del emprendedor.
     * @return lista de entregas; vacía si no hay entregas.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<EntregaDTO> obtenerEntregasEmprendedor(String id) throws NegocioException;

    /**
     * Obtiene la lista de entregas disponibles.
     *
     * @return lista de entregas.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<EntregaDTO> obtenerEntregasDisponibles() throws NegocioException;
}
