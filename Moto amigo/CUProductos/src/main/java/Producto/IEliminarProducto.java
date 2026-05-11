package Producto;

import com.mycompany.motoamigodto.EliminarProductoDTO;

/**
 * @author joset
 */
public interface IEliminarProducto {

    /**
     * Elimina un producto identificado por el DTO proporcionado.
     *
     * @param producto datos del producto a eliminar (contiene su id).
     * @return true si se eliminó exitosamente, false en caso contrario.
     */
    boolean eliminar(EliminarProductoDTO producto)throws ProductoException;
}
