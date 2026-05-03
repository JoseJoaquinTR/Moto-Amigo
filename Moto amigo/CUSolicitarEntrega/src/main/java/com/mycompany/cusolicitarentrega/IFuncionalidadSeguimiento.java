package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Caso de uso de seguimiento de entrega en tiempo real.
 *
 * @author joset
 */
public interface IFuncionalidadSeguimiento {

    /**
     * Obtiene la siguiente ubicación a recorrer en la ruta de seguimiento.
     *
     * @return siguiente ubicación.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    UbicacionDTO obtenerSiguiente() throws NegocioException;

    /**
     * Indica si la ruta de seguimiento ha terminado.
     *
     * @return true si la ruta ha terminado.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    boolean haTerminado() throws NegocioException;

    /**
     * Obtiene los datos del repartidor asignado a la entrega.
     *
     * @param idRepartidor identificador del repartidor.
     * @return datos del repartidor.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    RepartidorDTO obtenerRepartidorAsignado(Long idRepartidor) throws NegocioException;
}
