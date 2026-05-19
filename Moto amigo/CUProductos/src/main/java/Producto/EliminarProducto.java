package Producto;

import Productos.ProductosBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.productosdto.EliminarProductoDTO;
import Productos.IProductosBO;


/**
 * @author joset
 */
public class EliminarProducto implements IEliminarProducto {
private final IProductosBO productoBO;
    public EliminarProducto() {
        this.productoBO = ProductosBO.getInstancia();
    }

    @Override
    public boolean eliminar(EliminarProductoDTO producto) throws ProductoException {
        if (producto.getId() == null || producto.getId().isBlank()) {
            throw new ProductoException("El id del producto a eliminar es obligatorio.");
        }
        try {
            return productoBO.eliminarProducto(producto.getId());
        } catch (NegocioException ex) {
            throw new ProductoException("Error al eliminar producto", ex);
        }
    }
}
