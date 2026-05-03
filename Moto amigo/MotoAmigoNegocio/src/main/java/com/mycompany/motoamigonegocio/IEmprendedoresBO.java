package com.mycompany.motoamigonegocio;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigodto.EmprendedorDTO;

/**
 * Operaciones de negocio relacionadas con emprendedores.
 *
 * @author Jesus Omar
 */
public interface IEmprendedoresBO {

    /**
     * Obtiene los datos de un emprendedor a partir de su identificador.
     *
     * @param id identificador del emprendedor.
     * @return datos del emprendedor; null si no se encuentra.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    EmprendedorDTO obtenerEmprendedorPorId(Long id) throws NegocioException;

    /**
     * Registra un nuevo emprendedor en el sistema.
     *
     * @param emprendedorDTO datos del emprendedor a registrar.
     * @return entidad del emprendedor registrado.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    Emprendedor registrarEmprendedor(EmprendedorDTO emprendedorDTO) throws NegocioException;
}
