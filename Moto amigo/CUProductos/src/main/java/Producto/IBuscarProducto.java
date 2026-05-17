package Producto;

import com.mycompany.productosdto.ProductoDTO;
import java.util.List;

/**
 * @author joset
 */
public interface IBuscarProducto {

    /**
     * Busca productos que coincidan con el criterio proporcionado.
     *
     * @param criterio texto de búsqueda.
     * @return lista de productos encontrados, o lista vacía si no hay
     * coincidencias.
     * @throws Producto.ProductoException
     */
    List<ProductoDTO> buscar(String criterio)throws ProductoException;
}