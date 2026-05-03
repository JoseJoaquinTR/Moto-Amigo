package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 * Caso de uso para buscar ubicaciones a partir de un texto de búsqueda.
 *
 * @author joset
 */
public interface IBuscarUbicacion {

    /**
     * Busca ubicaciones que coincidan con el texto proporcionado.
     *
     * @param texto cadena de búsqueda.
     * @return lista de ubicaciones coincidentes; vacía si el texto es nulo o
     * tiene menos de 3 caracteres.
     * @throws NegocioException si ocurre un error durante la búsqueda.
     */
    List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException;
}
