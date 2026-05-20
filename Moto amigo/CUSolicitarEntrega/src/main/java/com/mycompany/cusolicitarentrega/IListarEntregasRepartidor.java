package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 * Caso de uso para listar las entregas asociadas a un repartidor.
 *
 * @author Carmen Andrea Lara Osuna
 */
public interface IListarEntregasRepartidor {

    /**
     * Obtiene la lista de entregas asignadas al repartidor indicado.
     *
     * @param idRepartidor identificador del repartidor.
     * @return lista de entregas; vacía si no hay entregas.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<EntregaDTO> listarEntregasRepartidor(String idRepartidor) throws NegocioException;
    
    List<EntregaDTO> obtenerEntregasDisponibles() throws NegocioException;
}
