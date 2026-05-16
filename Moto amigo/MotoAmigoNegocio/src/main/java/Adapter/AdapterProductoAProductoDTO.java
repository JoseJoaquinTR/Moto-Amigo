
package Adapter;

import com.mycompany.Entidades.Producto;
import com.mycompany.productosdto.ProductoDTO;
import enums.TipoUnidadProductoDTO;

/**
 *
 * @author joset
 */
public class AdapterProductoAProductoDTO {

    public ProductoDTO adaptar(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPeso(producto.getPeso());
        if (producto.getUnidad() != null) {
            dto.setUnidad(TipoUnidadProductoDTO.valueOf(producto.getUnidad().name()));
        }
        dto.setPrecio(producto.getPrecio());
        dto.setImagen(producto.getImagen());
        dto.setIdEmprendedor(producto.getIdEmprendedor());
        return dto;
    }

}
