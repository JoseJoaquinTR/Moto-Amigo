package Adapter;

import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.TipoUnidadProducto;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.productosdto.ProductoDTO;
import enums.TipoUnidadProductoDTO;

/**
 *
 * @author joset
 */
public class AdapterProductoAProductoDTO {

    public ProductoDTO adaptarADTO(Producto producto) {
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
    public Producto adaptarAProductoEntidad(NuevoProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto entidad = new Producto();
        entidad.setNombre(dto.getNombre());
        entidad.setPeso(dto.getPeso());
        entidad.setUnidad(adaptarTipoUnidadAEntidad(dto.getUnidad()));
        entidad.setPrecio(dto.getPrecio());
        entidad.setImagen(dto.getImagen());
        entidad.setIdEmprendedor(dto.getIdEmprendedor());
        return entidad;
    }

    public Producto EditarProductoAdaptarAProductoEntidad(EditarProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto entidad = new Producto();
        entidad.setNombre(dto.getNombre());
        entidad.setPeso(dto.getPeso());
        entidad.setUnidad(adaptarTipoUnidadAEntidad(dto.getUnidad()));
        entidad.setPrecio(dto.getPrecio());
        entidad.setImagen(dto.getImagen());
        return entidad;
    }
    
    public TipoUnidadProducto adaptarTipoUnidadAEntidad(TipoUnidadProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        return TipoUnidadProducto.valueOf(dto.name());
    }

}
