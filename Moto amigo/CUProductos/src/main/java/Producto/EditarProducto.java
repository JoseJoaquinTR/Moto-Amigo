package Producto;

import Productos.IProductoBO;
import Productos.ProductoBO;
import com.mycompany.motoamigodto.EditarProductoDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import com.mycompany.motoamigonegocio.NegocioException;

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
