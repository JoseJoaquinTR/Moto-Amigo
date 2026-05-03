package com.mycompany.motoamigonegocio;

import Adapter.AdapterRepartidorARepartidorDTO;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigopersistencia.IRepartidorDAO;
import com.mycompany.motoamigopersistencia.RepartidorDAO;

/**
 * Implementación de la lógica de negocio para repartidores.
 *
 * @author joset
 */
public class RepartidorBO implements IRepartidorBO {

    private final IRepartidorDAO dao = RepartidorDAO.getInstance();

    @Override
    public RepartidorDTO obtenerRepartidorPorId(Long idRepartidor) throws NegocioException {
        if (idRepartidor == null) {
            throw new NegocioException("El identificador del repartidor no puede ser nulo.");
        }
        Repartidor repa = dao.obtenerRepartidorPorId(idRepartidor);
        return new AdapterRepartidorARepartidorDTO().adaptar(repa);
    }
}
