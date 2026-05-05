package com.mycompany.cuemprendedor;

import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Caso de uso para buscar un emprendedor por su identificador.
 *
 * @author Jesus Omar
 */
public interface IBuscarEmprendedorPorId {

    /**
     * Obtiene los datos de un emprendedor a partir de su identificador.
     *
     * @param id identificador del emprendedor.
     * @return datos del emprendedor; null si no se encuentra.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    EmprendedorDTO buscarEmprendedorPorId(Long id) throws NegocioException;
}
