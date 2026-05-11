
package Producto;
import com.mycompany.motoamigodto.EditarProductoDTO;
import com.mycompany.motoamigodto.ProductoDTO;

/**
 *
 * @author joset
 */
public interface IEditarProducto {

    /**
     * Actualiza un producto con los datos proporcionados.
     *
     * @param id identificador del producto a editar.
     * @param producto datos a actualizar.
     * @return el producto actualizado, o null si ocurre un error.
     */
    ProductoDTO editar(String id, EditarProductoDTO producto)throws ProductoException;
}