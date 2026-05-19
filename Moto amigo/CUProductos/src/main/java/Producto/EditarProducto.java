package Producto;

import Productos.ProductosBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.productosdto.ProductoDTO;
import Productos.IProductosBO;

/**
 * @author joset
 */
public class EditarProducto implements IEditarProducto {

    private final IProductosBO productoBO;

    public EditarProducto() {
        this.productoBO = ProductosBO.getInstancia();
    }

    @Override
    public ProductoDTO editar(String id, EditarProductoDTO producto) throws ProductoException {
        if (id == null || id.isBlank()) {
            throw new ProductoException("El id del producto a editar es obligatorio.");
        }
        if (producto == null) {
            throw new ProductoException("Los datos del producto a editar no pueden ser nulos.");
        }
        try {
            return productoBO.editarProducto(id, producto);
        } catch (NegocioException ex) {
            throw new ProductoException("Error al editar producto", ex);
        }
    }
}
