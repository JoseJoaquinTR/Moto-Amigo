package Adapter;

import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.TipoUnidadProducto;
import com.mycompany.productosdto.NuevoProductoDTO;
import enums.TipoUnidadProductoDTO;

/**
 *
 * @author joset
 */
public class AdapterProducto {

    private AdapterProducto() {
    }

    public static Producto aProductoDTO(NuevoProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto entidad = new Producto();
        entidad.setNombre(dto.getNombre());
        entidad.setPeso(dto.getPeso());
        entidad.setUnidad(aTipoUnidad(dto.getUnidad()));
        entidad.setPrecio(dto.getPrecio());
        entidad.setImagen(dto.getImagen());
        entidad.setIdEmprendedor(dto.getIdEmprendedor());
        return entidad;
    }

    public static TipoUnidadProducto aTipoUnidad(TipoUnidadProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        return TipoUnidadProducto.valueOf(dto.name());
    }

}
