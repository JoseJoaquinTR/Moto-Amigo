package Producto;

import Productos.IProductoBO;
import Productos.ProductoBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.productosdto.ProductoDTO;
import java.util.List;

/**
 * @author joset
 */
public class BuscarProducto implements IBuscarProducto {

    private final IProductoBO productoBO;

    public BuscarProducto() {
        this.productoBO = ProductoBO.getInstancia();
    }

    @Override
    public List<ProductoDTO> buscar(String criterio) throws ProductoException {
        try {
            return productoBO.buscarProductosPorNombre(criterio);
        } catch (NegocioException ex) {
            throw new ProductoException("No se encontro resultados " ,ex);
        }
    }
}
