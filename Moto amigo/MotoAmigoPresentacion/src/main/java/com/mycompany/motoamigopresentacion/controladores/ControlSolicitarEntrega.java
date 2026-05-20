package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.cusolicitarentrega.BuscarUbicacion;
import com.mycompany.cusolicitarentrega.ConsultarRuta;
import com.mycompany.cusolicitarentrega.IBuscarUbicacion;
import com.mycompany.cusolicitarentrega.IConsultarRuta;
import com.mycompany.cusolicitarentrega.IListarEntregasEmprendedor;
import com.mycompany.cusolicitarentrega.IObtenerRepartidoresDisponibles;
import com.mycompany.cusolicitarentrega.IPublicarSolicitud;
import com.mycompany.cusolicitarentrega.ListarEntregasEmprendedor;
import com.mycompany.cusolicitarentrega.ObtenerRepartidoresDisponibles;
import com.mycompany.cusolicitarentrega.PublicarSolicitud;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.Observers.EmprendedorObserver;
import com.mycompany.motoamigonegocio.Observers.GestorNotificacionesEntrega;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 * Controlador del flujo en el que un emprendedor publica una solicitud de
 * entrega y espera a que algún repartidor la acepte mediante el patrón Observer.
 *
 * @author joset
 */
public class ControlSolicitarEntrega {
    private final IListarEntregasEmprendedor cuListarEntregasEmprendedor;
    private static ControlSolicitarEntrega instancia;

    private final IObtenerRepartidoresDisponibles cuObtenerRep;
    private final IPublicarSolicitud cuPublicar;
    private final IConsultarRuta cuConsultarRuta;
    private final IBuscarUbicacion cuBuscarUbicacion;
    private EmprendedorObserver emprendedorObserver;

    private ControlSolicitarEntrega() {
        this.cuObtenerRep = ObtenerRepartidoresDisponibles.crear();
        this.cuPublicar = PublicarSolicitud.crear();
        this.cuConsultarRuta = ConsultarRuta.crear();
        this.cuBuscarUbicacion = BuscarUbicacion.crear();
        this.cuListarEntregasEmprendedor = ListarEntregasEmprendedor.crear();
    }

    /**
     * Obtiene la instancia única del controlador.
     *
     * @return instancia de ControlSolicitarEntrega.
     */
    public static ControlSolicitarEntrega getInstance() {
        if (instancia == null) {
            instancia = new ControlSolicitarEntrega();
        }
        return instancia;
    }

    /**
     * Obtiene la lista de repartidores disponibles.
     *
     * @return lista de repartidores disponibles; lista vacía si ocurre un error.
     */
    public List<RepartidorDTO> obtenerRepartidoresDisponibles() {
        try {
            return cuObtenerRep.obtenerRepartidoresDisponibles();
        } catch (NegocioException ex) {
            Logger.getLogger(ControlSolicitarEntrega.class.getName()).log(Level.SEVERE, "Error obteniendo repartidores disponibles", ex);
            return Collections.emptyList();
        }
    }

    /**
     * Publica una solicitud de entrega.
     *
     * @param solicitud datos de la solicitud.
     * @return true si la publicación fue exitosa; false si ocurre un error o
     * los datos no son válidos.
     */
    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) {
        try {
            return cuPublicar.publicarSolicitud(solicitud);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlSolicitarEntrega.class.getName()).log(Level.SEVERE, "Error publicando solicitud", ex);
            return false;
        }
    }

    /**
     * Calcula la ruta entre dos direcciones.
     *
     * @param origen dirección de origen.
     * @param destino dirección de destino.
     * @return respuesta con la ruta calculada; null si ocurre un error.
     */
    public RutaResponseDTO obtenerRuta(String origen, String destino) {
        try {
            RutaRequestDTO request = new RutaRequestDTO(origen, destino);
            return cuConsultarRuta.consultarRuta(request);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlSolicitarEntrega.class.getName()).log(Level.SEVERE, "Error consultando ruta", ex);
            return null;
        }
    }

    /**
     * Busca ubicaciones que coincidan con el texto proporcionado.
     *
     * @param texto cadena de búsqueda.
     * @return lista de ubicaciones coincidentes; lista vacía si ocurre un error.
     */
    public List<UbicacionDTO> buscarUbicacion(String texto) {
        try {
            return cuBuscarUbicacion.buscarUbicacion(texto);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlSolicitarEntrega.class.getName()).log(Level.SEVERE, "Error buscando ubicaciones", ex);
            return Collections.emptyList();
        }
    }

    /**
     * Publica la solicitud y ejecuta el callback cuando un repartidor acepta.
     * El evento PEDIDO_ACEPTADO lo emite la pantalla del repartidor al aceptar.
     *
     * @param solicitud solicitud a publicar.
     * @param alAceptar callback a ejecutar cuando se acepte la solicitud.
     */
    public void publicarYEsperarAceptacion(SolicitudEntregaDTO solicitud, Runnable alAceptar) {
        boolean publicado = publicarSolicitud(solicitud);

        if (!publicado) {
            return;
        }

        Timer timer = new Timer(1000, null);

        timer.addActionListener(e -> {
            try {
                List<EntregaDTO> entregas = cuListarEntregasEmprendedor
                        .listarEntregasEmprendedor(solicitud.getIdEmprendedor());

                for (EntregaDTO entrega : entregas) {
                    if (entrega == null || entrega.getId() == null) {
                        continue;
                    }

                    if (!entrega.getId().equals(solicitud.getIdSolicitud())) {
                        continue;
                    }

                    boolean aceptada = entrega.getIdRepartidor() != null
                            && !entrega.getIdRepartidor().isBlank()
                            && "EN_CAMINO".equals(entrega.getEstadoEntrega());

                    if (aceptada) {
                        timer.stop();

                        if (alAceptar != null) {
                            alAceptar.run();
                        }
                    }

                    return;
                }

            } catch (NegocioException ex) {
                Logger.getLogger(ControlSolicitarEntrega.class.getName())
                        .log(Level.SEVERE, "Error verificando aceptación de la entrega", ex);
            }
        });

        timer.start();
    }

    private EmprendedorObserver configurarObserverEmprendedor() {
        GestorNotificacionesEntrega gestor = GestorNotificacionesEntrega.getInstance();

        if (emprendedorObserver != null) {
            gestor.eliminarObserver(emprendedorObserver);
        }

        EmprendedorObserver nuevoObserver = new EmprendedorObserver("Emprendedor");
        gestor.agregarObserver(nuevoObserver);
        return nuevoObserver;
    }

    /**
     * Se conserva por compatibilidad con pantallas existentes.
     *
     * @param solicitud solicitud cuyos observers se registran.
     */
    public void registrarObservers(SolicitudEntregaDTO solicitud) {
        configurarObserverEmprendedor();
    }
}
