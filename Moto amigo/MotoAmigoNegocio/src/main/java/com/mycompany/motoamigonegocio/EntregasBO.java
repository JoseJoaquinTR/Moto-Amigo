
package com.mycompany.motoamigonegocio;

import Adapter.AdapterEntregaAEntregaDTO;
import com.mycompany.Entidades.Entrega;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.List;

/**
 * Implementación de la lógica de negocio para entregas.
 *
 * @author Jesus Omar
 */
public class EntregasBO implements IEntregasBO {

    private final IFachadaPersistencia fachada = FachadaPersistencia.getInstancia();
    private final AdapterEntregaAEntregaDTO adapter = new AdapterEntregaAEntregaDTO();

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
    public List<EntregaDTO> obtenerEntregasRepartidor(String id) throws NegocioException {
        if (id == null || id.isBlank()) {
            throw new NegocioException("El identificador del repartidor no puede ser nulo o vacío.");
        }

        try {
            return adapter.adaptarLista(fachada.obtenerEntregasRepartidor(id));
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible obtener las entregas del repartidor.", ex);
        }
    }

    @Override
    public List<EntregaDTO> obtenerEntregasEmprendedor(String id) throws NegocioException {
        if (id == null || id.isBlank()) {
            throw new NegocioException("El identificador del emprendedor no puede ser nulo o vacío.");
        }

        try {
            return adapter.adaptarLista(fachada.obtenerEntregasEmprendedor(id));
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible obtener las entregas del emprendedor.", ex);
        }
    }
    @Override
    public List<EntregaDTO> obtenerEntregasDisponibles() throws NegocioException {
        try {
            return adapter.adaptarLista(fachada.obtenerEntregasDisponibles());
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible obtener las entregas disponibles.", ex);
        }
    }
    @Override
    public EntregaDTO aceptarEntrega(String idEntrega, String idRepartidor) throws NegocioException {
        try {
            Entrega actualizada = fachada.actualizarEntrega(idEntrega, idRepartidor, "EN_CAMINO");
            if (actualizada == null) {
                throw new NegocioException("No se encontró la entrega.");
            }
            return adapter.adaptar(actualizada);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al aceptar la entrega.", ex);
        }
    }
    
    @Override
    public EntregaDTO finalizarEntrega(String idEntrega) throws NegocioException {
        try {
            Entrega actualizada = fachada.actualizarEntrega(idEntrega, null, "COMPLETADA");
            if (actualizada == null) {
                throw new NegocioException("No se encontró la entrega.");
            }
            return adapter.adaptar(actualizada);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al finalizar la entrega.", ex);
        }
    }
}