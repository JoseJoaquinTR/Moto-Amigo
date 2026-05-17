package Producto;

import Productos.IProductoBO;
import Productos.ProductoBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.productosdto.ProductoDTO;

/**
 * @author joset
 */
public class EditarProducto implements IEditarProducto {

    private final IProductoBO productoBO;

    public EditarProducto() {
        this.productoBO = ProductoBO.getInstancia();
    }

    @Override
    public ProductoDTO editar(String id, EditarProductoDTO producto) throws ProductoException {
        try {
            return productoBO.editarProducto(id, producto);
        } catch (NegocioException ex) {
            throw new ProductoException("Error al editar producto", ex);
        }
    }
}
