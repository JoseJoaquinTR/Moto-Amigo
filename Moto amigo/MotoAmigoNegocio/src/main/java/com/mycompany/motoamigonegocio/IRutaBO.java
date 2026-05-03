package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;

/**
 * Operaciones de negocio relacionadas con el cálculo y seguimiento de rutas.
 *
 * @author calo2
 */
public interface IRutaBO {

    /**
     * Calcula la ruta entre los puntos indicados en la solicitud.
     *
     * @param request datos para el cálculo de ruta.
     * @return respuesta con la información de la ruta.
     * @throws NegocioException si ocurre un error durante el cálculo.
     */
    RutaResponseDTO calcularRuta(RutaRequestDTO request) throws NegocioException;

    /**
     * Indica si la ruta de seguimiento ha terminado.
     *
     * @return true si la ruta ha terminado.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    boolean haTerminadoRuta() throws NegocioException;

    /**
     * Obtiene la siguiente ubicación en la ruta de seguimiento.
     *
     * @return siguiente ubicación.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    UbicacionDTO obtenerSiguienteUbicacion() throws NegocioException;
}
