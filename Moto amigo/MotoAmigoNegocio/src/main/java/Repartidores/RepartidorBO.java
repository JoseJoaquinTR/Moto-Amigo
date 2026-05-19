
package Repartidores;

import static Adapter.AdapterRepartidorARepartidorDTO.*;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import enums.Estado;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RepartidorBO implements IRepartidoresBO {

    private static RepartidoresBO instancia;

    private final IFachadaPersistencia persistencia;

    public RepartidorBO() {
        this.persistencia = FachadaPersistencia.getInstancia();
    }

    public static synchronized RepartidoresBO getInstancia() {
        if (instancia == null) {
            instancia = new RepartidoresBO();
        }
        return instancia;
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidoresActivos() throws NegocioException {
        try {
            return adaptarLista(persistencia.obtenerRepartidoresActivos());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener repartidores activos.", ex);
        }
    }

    @Override
    public RepartidorDTO cambiarEstadoRepartidor(String id, Estado estado) throws NegocioException {
        try {
            return toDTO( persistencia.cambiarEstadoRepartidor(id, estado));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cambiar estado del repartidor.", ex);
        }
    }

    @Override
    public RepartidorDTO consultarRepartidorPorId(String id) throws NegocioException {
        try {
            return toDTO(persistencia.consultarRepartidorPorId(id));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar repartidor por id.", ex);
        }
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidores() throws NegocioException {
        try {
            return adaptarLista(persistencia.obtenerRepartidores());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener repartidores.", ex);
        }
    }

    @Override
    public List<RepartidorDTO> buscarRepartidoresPorNombre(String nombre) throws NegocioException {
        try {
            return adaptarLista(persistencia.buscarRepartidor(nombre));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar repartidores por nombre.", ex);
        }
    }

    @Override
    public RepartidorDTO incrementarNumeroBloqueos(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
