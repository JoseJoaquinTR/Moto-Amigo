package com.mycompany.motoamigonegocio;

import Adapter.AdapterEntregaAEntregaDTO;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigopersistencia.EntregasDAO;
import com.mycompany.motoamigopersistencia.IEntregasDAO;
import java.util.List;

/**
 * Implementación de la lógica de negocio para entregas.
 *
 * @author Jesus Omar
 */
public class EntregasBO implements IEntregasBO {

    private final IEntregasDAO dao = EntregasDAO.getInstancia();

    private static EntregasBO instancia;

    private EntregasBO() {
    }

    /**
     * Obtiene la instancia única del BO.
     *
     * @return instancia de EntregasBO.
     */
    public static synchronized EntregasBO getInstance() {
        if (instancia == null) {
            instancia = new EntregasBO();
        }
        return instancia;
    }

    @Override
    public List<EntregaDTO> obtenerEntregasRepartidor(Long id) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El identificador del repartidor no puede ser nulo.");
        }
        return new AdapterEntregaAEntregaDTO().adaptarLista(dao.obtenerEntregasRepartidor(id));
    }

    @Override
    public List<EntregaDTO> obtenerEntregasEmprendedor(Long id) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El identificador del emprendedor no puede ser nulo.");
        }
        return new AdapterEntregaAEntregaDTO().adaptarLista(dao.obtenerEntregasEmprendedor(id));
    }
}
