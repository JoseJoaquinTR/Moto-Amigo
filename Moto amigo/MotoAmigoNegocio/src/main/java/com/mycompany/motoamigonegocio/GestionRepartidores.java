package com.mycompany.motoamigonegocio;

import Adapter.AdapterEntregaAEntregaDTO;
import Adapter.AdapterRepartidorARepartidorDTO;
import com.mycompany.Entidades.Entrega;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.Observers.EventoEntrega;
import com.mycompany.motoamigonegocio.Observers.GestorNotificacionesEntrega;
import com.mycompany.motoamigopersistencia.IRepartidoresDAO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.motoamigopersistencia.RepartidoresDAO;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.List;

/**
 * Implementación de la gestión de repartidores y publicación de solicitudes.
 *
 * @author xiomi
 */
public class GestionRepartidores implements IGestionRepartidores {

    private static GestionRepartidores instancia;

    private final IRepartidoresDAO repartidoresDAO;
    private final AdapterEntregaAEntregaDTO adapter;
    private final IFachadaPersistencia fachada = FachadaPersistencia.getInstancia();

    private GestionRepartidores() {
        this.repartidoresDAO = RepartidoresDAO.getInstancia();
        adapter = new AdapterEntregaAEntregaDTO();
    }

    /**
     * Obtiene la instancia única del BO.
     *
     * @return instancia de GestionRepartidores.
     */
    public static synchronized GestionRepartidores getInstance() {
        if (instancia == null) {
            instancia = new GestionRepartidores();
        }
        return instancia;
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidoresDisponibles() throws NegocioException {
        try {
            List<Repartidor> lista = repartidoresDAO.obtenerActivos();
            return new AdapterRepartidorARepartidorDTO().adaptarLista(lista);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener repartidores disponibles.", ex);
        }
    }

    @Override
    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) throws NegocioException {
        if (solicitud == null || solicitud.getOrigen() == null || solicitud.getDestino() == null) {
            return false;
        }

        try {
            Entrega entidad = adapter.adaptarAEntidad(solicitud);

            Entrega guardada = fachada.agregarEntrega(entidad);

            solicitud.setIdSolicitud(guardada.getId());
            solicitud.setEstado("DISPONIBLE");

            GestorNotificacionesEntrega.getInstance().notificar(
                    EventoEntrega.PEDIDO_DISPONIBLE, solicitud);

            return true;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al publicar la solicitud: " + ex.getMessage(), ex);
        }
    }
}
