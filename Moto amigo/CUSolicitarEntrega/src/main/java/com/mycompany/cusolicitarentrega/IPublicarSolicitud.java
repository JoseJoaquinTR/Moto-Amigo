package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Caso de uso para publicar una solicitud de entrega.
 *
 * @author Carmen Andrea Lara Osuna
 */
public interface IPublicarSolicitud {

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
