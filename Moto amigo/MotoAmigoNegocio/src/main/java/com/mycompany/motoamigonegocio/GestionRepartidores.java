package com.mycompany.motoamigonegocio;

import Adapter.AdapterRepartidorARepartidorDTO;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.Observer.EventoEntrega;
import com.mycompany.motoamigonegocio.Observer.GestorNotificacionesEntrega;
import com.mycompany.motoamigopersistencia.IRepartidorDAO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.motoamigopersistencia.RepartidorDAO;
import java.util.List;

/**
 * Implementación de la gestión de repartidores y publicación de solicitudes.
 *
 * @author xiomi
 */
public class GestionRepartidores implements IGestionRepartidores {

    private static GestionRepartidores instancia;

//    private final IRepartidorDAO repartidorDAO;

    private GestionRepartidores() {
//        this.repartidorDAO = new RepartidorDAO();
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
//        try {
//            List<Repartidor> lista = repartidorDAO.obtenerActivos();
//            return new AdapterRepartidorARepartidorDTO().adaptarLista(lista);
//        } catch (PersistenciaException ex) {
//            throw new NegocioException("Error al obtener repartidores disponibles.", ex);
//        }
        return null;
//        try {
//            List<Repartidor> lista = repartidorDAO.obtenerActivos();
//            return new AdapterRepartidorARepartidorDTO().adaptarLista(lista);
//        } catch (PersistenciaException ex) {
//            throw new NegocioException("Error al obtener repartidores disponibles.", ex);
//        }
    }

    @Override
    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) throws NegocioException {
        if (solicitud == null || solicitud.getOrigen() == null || solicitud.getDestino() == null) {
            return false;
        }
        GestorNotificacionesEntrega.getInstance().notificar(EventoEntrega.PEDIDO_DISPONIBLE, solicitud);
        return true;
    }
}