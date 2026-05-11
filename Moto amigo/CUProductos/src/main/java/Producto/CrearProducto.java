package Producto;

import Productos.IProductoBO;
import Productos.ProductoBO;
import com.mycompany.motoamigodto.NuevoProductoDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * @author joset
 */
public class CrearProducto implements ICrearProducto {

    private final IProductoBO productoBO;

    public CrearProducto() {
        this.productoBO = ProductoBO.getInstancia();
    }

    @Override
    public ProductoDTO crear(NuevoProductoDTO producto) throws ProductoException {
        try {
            return productoBO.crearProducto(producto);
        } catch (NegocioException ex) {
              throw new ProductoException("Error al crear producto" ,ex);
        }
    }
}
