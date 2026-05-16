package Producto;

import Productos.IProductoBO;
import Productos.ProductoBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.productosdto.EliminarProductoDTO;


/**
 * @author joset
 */
public class EliminarProducto implements IEliminarProducto {
private final IProductoBO productoBO;
    public EliminarProducto() {
        this.productoBO = ProductoBO.getInstancia();
    }

    @Override
    public boolean eliminar(EliminarProductoDTO producto) throws ProductoException {
        try {
            return productoBO.eliminarProducto(producto.getId());
        } catch (NegocioException ex) {
            throw new ProductoException("Error al eliminar producto", ex);
        }
    }
}
