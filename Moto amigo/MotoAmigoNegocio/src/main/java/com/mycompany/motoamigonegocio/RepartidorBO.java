
package com.mycompany.motoamigonegocio;

import Adapter.AdapterRepartidorARepartidorDTO;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigopersistencia.IRepartidorDAO;
import com.mycompany.motoamigopersistencia.RepartidorDAO;

/**
 *
 * @author joset
 */
public class RepartidorBO implements IRepartidorBO{

    private IRepartidorDAO dao = RepartidorDAO.getInstance();

    @Override
    public RepartidorDTO obtenerRepartidorPorId(Long idRepartidor) {
        Repartidor repa = dao.obtenerRepartidorPorId(idRepartidor); 
        return new AdapterRepartidorARepartidorDTO().adaptar(repa);
    }
}
