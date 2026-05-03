package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 * Caso de uso para listar las entregas asociadas a un emprendedor.
 *
 * @author Carmen Andrea Lara Osuna
 */
public interface IListarEntregasEmprendedor {

    /**
     * Obtiene la lista de entregas asociadas al emprendedor indicado.
     *
     * @param idEmprendedor identificador del emprendedor.
     * @return lista de entregas; vacía si no hay entregas.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<EntregaDTO> listarEntregasEmprendedor(Long idEmprendedor) throws NegocioException;
}
