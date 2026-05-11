
package fachada;

import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.motoamigodto.EditarPaqueteDTO;
import com.mycompany.motoamigodto.EditarProductoDTO;
import com.mycompany.motoamigodto.NuevoPaqueteDTO;
import com.mycompany.motoamigodto.NuevoProductoDTO;
import com.mycompany.motoamigopersistencia.IPaqueteDAO;
import com.mycompany.motoamigopersistencia.IProductoDAO;
import com.mycompany.motoamigopersistencia.PaqueteDAO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.motoamigopersistencia.ProductoDAO;
import java.util.List;

/**
 * 
 * @author joset
 */
public class FachadaPersistencia implements IFachadaPersistencia {

    private static FachadaPersistencia instancia;

    private final IProductoDAO productoDAO;
    private final IPaqueteDAO paqueteDAO;

    private FachadaPersistencia() {
        this.productoDAO = ProductoDAO.getInstancia();
        this.paqueteDAO = PaqueteDAO.getInstancia();
    }

    public static synchronized FachadaPersistencia getInstancia() {
        if (instancia == null) {
            instancia = new FachadaPersistencia();
        }
        return instancia;
    }

    @Override
    public Producto agregarProducto(NuevoProductoDTO producto) throws PersistenciaException {
        return productoDAO.agregar(producto);
    }

    @Override
    public Producto actualizarProducto(String id, EditarProductoDTO datosNuevos) throws PersistenciaException {
        return productoDAO.actualizar(id, datosNuevos);
    }

    @Override
    public boolean eliminarProducto(String id) throws PersistenciaException {
        return productoDAO.eliminar(id);
    }

    @Override
    public Producto consultarProductoPorId(String id) throws PersistenciaException {
        return productoDAO.consultarPorId(id);
    }

    @Override
    public List<Producto> consultarProductosPorNombre(String nombreSimilar) throws PersistenciaException {
        return productoDAO.consultarPorNombre(nombreSimilar);
    }

    @Override
    public List<Producto> obtenerProductosPorEmprendedor(String idEmprendedor) throws PersistenciaException {
        return productoDAO.obtenerPorEmprendedor(idEmprendedor);
    }

    @Override
    public Paquete agregarPaquete(NuevoPaqueteDTO paquete) throws PersistenciaException {
        return paqueteDAO.agregar(paquete);
    }

    @Override
    public Paquete actualizarPaquete(String id, EditarPaqueteDTO datosNuevos) throws PersistenciaException {
        return paqueteDAO.actualizar(id, datosNuevos);
    }

    @Override
    public boolean eliminarPaquete(String id) throws PersistenciaException {
        return paqueteDAO.eliminar(id);
    }

    @Override
    public Paquete consultarPaquetePorId(String id) throws PersistenciaException {
        return paqueteDAO.consultarPorId(id);
    }

    @Override
    public List<Paquete> consultarPaquetesPorNombre(String nombre) throws PersistenciaException {
        return paqueteDAO.consultarPorNombre(nombre);
    }

    @Override
    public List<Paquete> obtenerPaquetesPorEmprendedor(String idEmprendedor) throws PersistenciaException {
        return paqueteDAO.obtenerPorEmprendedor(idEmprendedor);
    }

}
