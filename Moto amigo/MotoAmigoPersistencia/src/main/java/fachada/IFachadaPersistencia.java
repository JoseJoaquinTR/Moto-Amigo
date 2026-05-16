
package fachada;

import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import java.util.List;

/**
 * 
 * @author joset
 */
public interface IFachadaPersistencia {

    Producto agregarProducto(NuevoProductoDTO producto) throws PersistenciaException;

    Producto actualizarProducto(String id, EditarProductoDTO datosNuevos) throws PersistenciaException;

    boolean eliminarProducto(String id) throws PersistenciaException;

    Producto consultarProductoPorId(String id) throws PersistenciaException;

    List<Producto> consultarProductosPorNombre(String nombreSimilar) throws PersistenciaException;

    List<Producto> obtenerProductosPorEmprendedor(String idEmprendedor) throws PersistenciaException;

    Paquete agregarPaquete(NuevoPaqueteDTO paquete) throws PersistenciaException;

    Paquete actualizarPaquete(String id, EditarPaqueteDTO datosNuevos) throws PersistenciaException;

    boolean eliminarPaquete(String id) throws PersistenciaException;

    Paquete consultarPaquetePorId(String id) throws PersistenciaException;

    List<Paquete> consultarPaquetesPorNombre(String nombre) throws PersistenciaException;

    List<Paquete> obtenerPaquetesPorEmprendedor(String idEmprendedor) throws PersistenciaException;

}
