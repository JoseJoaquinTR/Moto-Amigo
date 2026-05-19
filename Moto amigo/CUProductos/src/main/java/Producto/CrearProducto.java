package Producto;

import Productos.ProductosBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.productosdto.ProductoDTO;
import Productos.IProductosBO;

/**
 * @author joset
 */
public class CrearProducto implements ICrearProducto {

    private final IProductosBO productoBO;

    public CrearProducto() {
        this.productoBO = ProductosBO.getInstancia();
    }

    @Override
    public ProductoDTO crear(NuevoProductoDTO producto) throws ProductoException {
        if (producto == null) {
            throw new ProductoException("El producto a crear no puede ser nulo.");
        }
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ProductoException("El nombre del producto es obligatorio.");
        }
        if (producto.getPeso() <= 0) {
            throw new ProductoException("El peso del producto debe ser mayor a 0.");
        }
        if (producto.getPrecio() <= 0) {
            throw new ProductoException("El precio del producto debe ser mayor a 0.");
        }
        if (producto.getUnidad() == null) {
            throw new ProductoException("La unidad del producto es obligatoria.");
        }
        try {
            return productoBO.crearProducto(producto);
        } catch (NegocioException ex) {
              throw new ProductoException("Error al crear producto" ,ex);
        }
    }
}
