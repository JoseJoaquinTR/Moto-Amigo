package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;

/**
 * Implementación del caso de uso para publicar una solicitud de entrega.
 * Delega la publicación a la fachada de negocio.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class PublicarSolicitud implements IPublicarSolicitud {

    private final IFachadaNegocio fachada;

    private PublicarSolicitud(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static PublicarSolicitud crear() {
        return new PublicarSolicitud(FachadaNegocio.crear());
    }

    @Override
    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) throws NegocioException {
        return fachada.publicarSolicitud(solicitud);
    }
}
