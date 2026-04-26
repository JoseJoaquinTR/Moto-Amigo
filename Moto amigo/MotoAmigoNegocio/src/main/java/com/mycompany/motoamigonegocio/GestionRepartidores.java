package com.mycompany.motoamigonegocio;

import java.util.List;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigopersistencia.IRepartidorDAO;
import com.mycompany.motoamigopersistencia.RepartidorDAO;

public class GestionRepartidores implements IGestionRepartidores {

    private static GestionRepartidores instancia;

    private IRepartidorDAO repartidorDAO;

    private GestionRepartidores() {
        this.repartidorDAO = RepartidorDAO.getInstance(); 
    }

    public static GestionRepartidores getInstance() {
        if (instancia == null) {
            instancia = new GestionRepartidores();
        }
        return instancia;
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidoresDisponibles() {
        return repartidorDAO.obtenerRepartidoresDisponibles();
    }

    @Override
    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) {

        if (solicitud == null || solicitud.getOrigen() == null || solicitud.getDestino() == null) {
            return false;
        }
        return true;
    }
}