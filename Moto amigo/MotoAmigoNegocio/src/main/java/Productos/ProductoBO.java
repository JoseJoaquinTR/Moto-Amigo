
package Productos;

import Adapter.AdapterProductoAProductoDTO;
import com.mycompany.Entidades.Producto;
import com.mycompany.motoamigodto.EditarProductoDTO;
import com.mycompany.motoamigodto.NuevoProductoDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class ProductoBO implements IProductoBO {

    private static ProductoBO instancia;

    private final IFachadaPersistencia fachada;
    private final AdapterProductoAProductoDTO adapter;

    private ProductoBO() {
        this.fachada = FachadaPersistencia.getInstancia();
        this.adapter = new AdapterProductoAProductoDTO();
    }

    public static synchronized ProductoBO getInstancia() {
        if (instancia == null) {
            instancia = new ProductoBO();
        }
        return instancia;
    }

    @Override
    public ProductoDTO crearProducto(NuevoProductoDTO nuevoProducto) throws NegocioException {
        if (nuevoProducto == null) {
            throw new NegocioException("El producto a crear no puede ser nulo.");
        }
        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().isBlank()) {
            throw new NegocioException("El nombre del producto es obligatorio.");
        }
        if (nuevoProducto.getPeso() <= 0) {
            throw new NegocioException("El peso del producto debe ser mayor a 0.");
        }
        if (nuevoProducto.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a 0.");
        }
        if (nuevoProducto.getUnidad() == null) {
            throw new NegocioException("La unidad del producto es obligatoria.");
        }

        try {
            Producto creado = fachada.agregarProducto(nuevoProducto);
            return adapter.adaptar(creado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al crear el producto.", ex);
        }
    }

    @Override
    public ProductoDTO editarProducto(String id, EditarProductoDTO datosNuevos) throws NegocioException {
        if (id == null || id.isBlank()) {
            throw new NegocioException("El id del producto a editar es obligatorio.");
        }
        if (datosNuevos == null) {
            throw new NegocioException("Los datos del producto a editar no pueden ser nulos.");
        }

        try {
            Producto actualizado = fachada.actualizarProducto(id, datosNuevos);
            return adapter.adaptar(actualizado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al editar el producto.", ex);
        }
    }

    @Override
    public boolean eliminarProducto(String id) throws NegocioException {
        if (id == null || id.isBlank()) {
            throw new NegocioException("El id del producto a eliminar es obligatorio.");
        }
        try {
            return fachada.eliminarProducto(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el producto.", ex);
        }
    }

    @Override
    public ProductoDTO consultarProductoPorId(String id) throws NegocioException {
        if (id == null || id.isBlank()) {
            throw new NegocioException("El id del producto a consultar es obligatorio.");
        }
        try {
            Producto encontrado = fachada.consultarProductoPorId(id);
            return adapter.adaptar(encontrado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el producto.", ex);
        }
    }

    @Override
    public List<ProductoDTO> buscarProductosPorNombre(String nombreSimilar) throws NegocioException {
        try {
            List<Producto> entidades = fachada.consultarProductosPorNombre(nombreSimilar);
            List<ProductoDTO> resultado = new ArrayList<>();
            for (Producto p : entidades) {
                resultado.add(adapter.adaptar(p));
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
                resultado.add(adapter.adaptar(p));
            }
            return resultado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener los productos del emprendedor.", ex);
        }
    }

}
