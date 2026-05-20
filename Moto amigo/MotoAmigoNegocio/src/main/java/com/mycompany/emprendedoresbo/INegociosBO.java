package com.mycompany.emprendedoresbo;

import com.mycompany.emprendedoresdto.DireccionDTO;
import com.mycompany.emprendedoresdto.NegocioDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author Jesus Omar
 */
public interface INegociosBO {

    /**
     * Metodo para registrar un nuevo negocio
     *
     * @param idEmprendedor el id del emprendedor que esta asociado al negocio
     * @param nuevoNegocio los datos del negocio
     * @return la DTO del nuevo negocio
     * @throws NegocioException
     */
    NegocioDTO registrarNegocio(String idEmprendedor, NegocioDTO nuevoNegocio) throws NegocioException;

    /**
     * Metodo para obtener un negocio por el id del emprendedor que tenga
     * asociado
     *
     * @param idEmprendedor id del emprendedor qu esta asociado al negocio
     * @return el negocio que se encontro
     * @throws NegocioException
     */
    NegocioDTO obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    /**
     * Metodo para obtener la direccion de un negocio por su id
     *
     * @param idNegocio el id del negocio del que se quiere obtener la direccion
     * @return la direccion del negocio
     * @throws NegocioException
     */
    DireccionDTO obtenerDireccionPorIdNegocio(String idNegocio) throws NegocioException;

    /**
     * Metodo para actualizar la direccion de un negocio
     *
     * @param idNegocio el id del negocio al que se le actualizara la direccion
     * @param direccion los datos de la nueva direccion
     * @return la direccion actualizada
     * @throws NegocioException
     */
    DireccionDTO actualizarDireccion(String idNegocio, DireccionDTO direccion) throws NegocioException;

}
