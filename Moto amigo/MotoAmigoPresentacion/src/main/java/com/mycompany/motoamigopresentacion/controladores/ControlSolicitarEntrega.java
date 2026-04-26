
package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.GestionRepartidores;
import com.mycompany.motoamigonegocio.IGestionRepartidores;
import java.util.List;

public class ControlSolicitarEntrega {

    private static ControlSolicitarEntrega instancia;

    private IGestionRepartidores gestionRepartidores;

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
}