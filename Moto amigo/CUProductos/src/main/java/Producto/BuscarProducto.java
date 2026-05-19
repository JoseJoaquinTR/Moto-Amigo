package Producto;

import Productos.ProductosBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.productosdto.ProductoDTO;
import java.util.List;
import Productos.IProductosBO;

/**
 * @author joset
 */
public class BuscarProducto implements IBuscarProducto {

    private final IProductosBO productoBO;

    public BuscarProducto() {
        this.productoBO = ProductosBO.getInstancia();
    }

    @Override
    public List<ProductoDTO> buscar(String criterio,String idEmprendedor) throws ProductoException {
        try {
            return productoBO.buscarProductosPorNombre(criterio,idEmprendedor);
        } catch (NegocioException ex) {
            throw new ProductoException("No se encontro resultados " ,ex);
        }
    }
}
