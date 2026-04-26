package com.mycompany.motoamigonegocio;

import Adapter.AdapterRepartidorARepartidorDTO;
import com.mycompany.Entidades.Repartidor;
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
        List<Repartidor> lista = repartidorDAO.obtenerRepartidoresDisponibles();
        return new AdapterRepartidorARepartidorDTO().adaptarLista(lista);
    }

    @Override
    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) {

        if (solicitud == null || solicitud.getOrigen() == null || solicitud.getDestino() == null) {
            return false;
        }
        return true;
    }
}