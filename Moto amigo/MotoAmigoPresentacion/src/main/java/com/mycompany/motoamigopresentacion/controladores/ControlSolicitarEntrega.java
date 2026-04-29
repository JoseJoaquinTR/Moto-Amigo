package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.cusolicitarentrega.ConsultarRuta;
import com.mycompany.cusolicitarentrega.IConsultarRuta;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.GestionRepartidores;
import com.mycompany.motoamigonegocio.IGestionRepartidores;
import com.mycompany.motoamigonegocio.Observer.EmprendedorObserver;
import com.mycompany.motoamigonegocio.Observer.GestorNotificacionesEntrega;
import java.util.List;
import javax.swing.Timer;

/**
 * Controlador del flujo: emprendedor publica solicitud y espera a que
 * algún repartidor la acepte mediante Observer.
 */
public class ControlSolicitarEntrega {

    private static ControlSolicitarEntrega instancia;

    private final IGestionRepartidores gestionRepartidores;
    private EmprendedorObserver emprendedorObserver;

    private ControlSolicitarEntrega() {
        this.gestionRepartidores = GestionRepartidores.getInstance();
    }

    public static synchronized ControlSolicitarEntrega getInstance() {
        if (instancia == null) {
            instancia = new ControlSolicitarEntrega();
        }
        return instancia;
    }

    public List<RepartidorDTO> obtenerRepartidoresDisponibles() {
        return gestionRepartidores.obtenerRepartidoresDisponibles();
    }

    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) {
        return gestionRepartidores.publicarSolicitud(solicitud);
    }

    public RutaResponseDTO obtenerRuta(String origen, String destino) {
        IConsultarRuta cu = new ConsultarRuta();
        RutaRequestDTO request = new RutaRequestDTO(origen, destino);
        return cu.consultarRuta(request);
    }

    /**
     * Publica la solicitud y ejecuta el callback cuando un repartidor acepta.
     * El evento PEDIDO_ACEPTADO lo emite la pantalla del repartidor al aceptar.
     */
    public void publicarYEsperarAceptacion(SolicitudEntregaDTO solicitud, Runnable alAceptar) {
        emprendedorObserver = configurarObserverEmprendedor();

        boolean publicado = publicarSolicitud(solicitud);
        if (!publicado) {
            return;
        }

        Timer timer = new Timer(1000, null);
        timer.addActionListener(e -> {
            if (emprendedorObserver.isPedidoAceptado()) {
                timer.stop();
                GestorNotificacionesEntrega.getInstance().eliminarObserver(emprendedorObserver);
                if (alAceptar != null) {
                    alAceptar.run();
                }
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
     */
    public void registrarObservers(SolicitudEntregaDTO solicitud) {
        configurarObserverEmprendedor();
    }
}
