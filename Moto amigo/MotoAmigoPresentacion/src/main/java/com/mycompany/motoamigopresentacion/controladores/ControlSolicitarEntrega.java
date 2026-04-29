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
import com.mycompany.motoamigonegocio.Observer.RepartidorObserver;
import java.util.List;
import javax.swing.Timer;

public class ControlSolicitarEntrega {

    private static ControlSolicitarEntrega instancia;

    private IGestionRepartidores gestionRepartidores;

    private boolean pedidoAceptado = false;

    private EmprendedorObserver emprendedorObserver;

    private ControlSolicitarEntrega() {
        this.gestionRepartidores = GestionRepartidores.getInstance();
    }

    public static ControlSolicitarEntrega getInstance() {
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

    public void publicarYEsperarAceptacion(SolicitudEntregaDTO solicitud, Runnable alAceptar) {
        emprendedorObserver = configurarObservers();

        publicarSolicitud(solicitud);

        Timer timer = new Timer(3000, null);
        timer.addActionListener(e -> {
            if (emprendedorObserver.isPedidoAceptado()) {
                timer.stop();
                GestorNotificacionesEntrega.getInstance().notificar(
                        "PEDIDO_ACEPTADO",
                        emprendedorObserver.getNombreRepartidorAsignado()
                );
                alAceptar.run();
            }
        });
        timer.start();
    }

    private EmprendedorObserver configurarObservers() {
        EmprendedorObserver emprendedorObserver = new EmprendedorObserver("Emprendedor");
        GestorNotificacionesEntrega gestor = GestorNotificacionesEntrega.getInstance();

        gestor.agregarObserver(emprendedorObserver);

        List<RepartidorDTO> disponibles = obtenerRepartidoresDisponibles();
        for (RepartidorDTO r : disponibles) {
            gestor.agregarObserver(new RepartidorObserver(r.getNombre()));
        }

        return emprendedorObserver;
    }
    public void registrarObservers(SolicitudEntregaDTO solicitud) {
        configurarObservers();
    }
}
