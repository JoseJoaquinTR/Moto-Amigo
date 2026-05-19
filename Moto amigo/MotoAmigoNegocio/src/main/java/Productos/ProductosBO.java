
package Productos;

import Adapter.AdapterProductoAProductoDTO;
import com.mycompany.Entidades.Producto;
import com.mycompany.productosdto.ProductoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.productosdto.NuevoProductoDTO;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class ProductosBO implements IProductosBO {

    private static ProductosBO instancia;

    private final IFachadaPersistencia fachada;
    private final AdapterProductoAProductoDTO adapter;

    private ProductosBO() {
        this.fachada = FachadaPersistencia.getInstancia();
        this.adapter = new AdapterProductoAProductoDTO();
    }

    public static synchronized ProductosBO getInstancia() {
        if (instancia == null) {
            instancia = new ProductosBO();
        }
        return instancia;
    }

    @Override
    public ProductoDTO crearProducto(NuevoProductoDTO nuevoProducto) throws NegocioException {
        try {
            Producto creado = fachada.agregarProducto(adapter.adaptarAProductoEntidad(nuevoProducto));
            return adapter.adaptarADTO(creado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al crear el producto.", ex);
        }
    }

    @Override
    public ProductoDTO editarProducto(String id, EditarProductoDTO datosNuevos) throws NegocioException {
        try {
            Producto actualizado = fachada.actualizarProducto(id, adapter.EditarProductoAdaptarAProductoEntidad(datosNuevos));
            return adapter.adaptarADTO(actualizado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al editar el producto.", ex);
        }
    }

    @Override
    public boolean eliminarProducto(String id) throws NegocioException {
        try {
            return fachada.eliminarProducto(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el producto.", ex);
        }
    }

    @Override
    public ProductoDTO consultarProductoPorId(String id) throws NegocioException {
        try {
            Producto encontrado = fachada.consultarProductoPorId(id);
            return adapter.adaptarADTO(encontrado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el producto.", ex);
        }
    }

    @Override
    public List<ProductoDTO> buscarProductosPorNombre(String criterio,String idEmprendedor) throws NegocioException {
        try {
            List<Producto> entidades = fachada.consultarProductosPorNombre(criterio,idEmprendedor);
            List<ProductoDTO> resultado = new ArrayList<>();
            for (Producto p : entidades) {
                resultado.add(adapter.adaptarADTO(p));
            }
            return resultado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar productos por nombre.", ex);
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorEmprendedor(String idEmprendedor) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio.");
        }
        try {
            List<Producto> entidades = fachada.obtenerProductosPorEmprendedor(idEmprendedor);
            List<ProductoDTO> resultado = new ArrayList<>();
            for (Producto p : entidades) {
                resultado.add(adapter.adaptarADTO(p));
            }
            return resultado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener los productos del emprendedor.", ex);
        }
    }

}
