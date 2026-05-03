package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.UbicacionDTO;
import java.util.List;

/**
 * Operaciones de negocio relacionadas con la búsqueda de ubicaciones.
 *
 * @author joset
 */
public interface IUbicacionBO {

    /**
     * Busca ubicaciones que coincidan con el texto proporcionado.
     *
     * @param texto cadena de búsqueda.
     * @return lista de ubicaciones coincidentes.
     * @throws NegocioException si ocurre un error durante la búsqueda.
     */
    List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException;
}
