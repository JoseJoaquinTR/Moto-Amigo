package Adapter;

import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.Entidades.TipoUnidadProducto;
import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
import enums.TipoUnidadProductoDTO;
import java.util.ArrayList;
import java.util.List;

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

    public static List<ProductosPaquete> aProductosPaquete(List<ProductosPaqueteDTO> dtos) {
        List<ProductosPaquete> resultado = new ArrayList<>();
        if (dtos == null) {
            return resultado;
        }
        for (ProductosPaqueteDTO ppDTO : dtos) {
            if (ppDTO == null || ppDTO.getProducto() == null || ppDTO.getProducto().getId() == null) {
                continue;
            }
            resultado.add(new ProductosPaquete(ppDTO.getProducto().getId(), ppDTO.getCantidad(), ppDTO.getPesoTotal()));
        }
        return resultado;
    }

}
