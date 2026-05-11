
package Productos;

import com.mycompany.motoamigodto.EditarProductoDTO;
import com.mycompany.motoamigodto.NuevoProductoDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 *
 * @author joset
 */
public interface IProductoBO {

    /**
     * Registra un nuevo producto en el sistema.
     *
     * @param nuevoProducto datos del producto a registrar.
     * @return el producto creado con su identificador asignado.
     * @throws NegocioException si los datos son inválidos o falla la persistencia.
     */
    ProductoDTO crearProducto(NuevoProductoDTO nuevoProducto) throws NegocioException;

    /**
     * Modifica los datos de un producto existente.
     *
     * @param id identificador del producto a editar.
     * @param datosNuevos campos del producto a actualizar.
     * @return el producto ya actualizado, o null si no se encontró.
     * @throws NegocioException si los datos son inválidos o falla la persistencia.
     */
    ProductoDTO editarProducto(String id, EditarProductoDTO datosNuevos) throws NegocioException;

    /**
     * Elimina un producto del sistema.
     *
     * @param id identificador del producto a eliminar.
     * @return true si se eliminó, false si no existía.
     * @throws NegocioException si falla la persistencia.
     */
    boolean eliminarProducto(String id) throws NegocioException;

    /**
     * Consulta un producto por su identificador.
     *
     * @param id identificador del producto.
     * @return el producto encontrado, o null si no existe.
     * @throws NegocioException si falla la persistencia.
     */
    ProductoDTO consultarProductoPorId(String id) throws NegocioException;

    /**
     * Busca productos cuyo nombre contenga el texto indicado.
     *
     * @param nombreSimilar fragmento del nombre a buscar.
     * @return lista de productos que coinciden.
     * @throws NegocioException si falla la persistencia.
     */
    List<ProductoDTO> buscarProductosPorNombre(String nombreSimilar) throws NegocioException;

    /**
     * Obtiene todos los productos pertenecientes a un emprendedor.
     *
     * @param idEmprendedor identificador del emprendedor.
     * @return lista de productos del emprendedor.
     * @throws NegocioException si falla la persistencia.
     */
    List<ProductoDTO> obtenerProductosPorEmprendedor(String idEmprendedor) throws NegocioException;

}
