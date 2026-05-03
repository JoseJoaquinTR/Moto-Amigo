package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import java.util.List;

/**
 * Operaciones de negocio relacionadas con la gestión de repartidores y la
 * publicación de solicitudes de entrega.
 *
 * @author xiomi
 */
public interface IGestionRepartidores {

    /**
     * Obtiene la lista de repartidores actualmente disponibles.
     *
     * @return lista de repartidores disponibles.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<RepartidorDTO> obtenerRepartidoresDisponibles() throws NegocioException;

    /**
     * Publica una solicitud de entrega para que los repartidores disponibles
     * puedan recibirla.
     *
     * @param solicitud datos de la solicitud a publicar.
     * @return true si la publicación fue exitosa, false en caso contrario.
     * @throws NegocioException si ocurre un error durante la publicación.
     */
    boolean publicarSolicitud(SolicitudEntregaDTO solicitud) throws NegocioException;
}
