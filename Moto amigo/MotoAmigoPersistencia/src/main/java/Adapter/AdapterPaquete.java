package Adapter;

import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.Entidades.TamañoPaquete;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
import enums.TamañoPaqueteDTO;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author joset
 */
public class AdapterPaquete {

    private AdapterPaquete() {
    }

    public static Paquete aPaqueteDTO(NuevoPaqueteDTO dto) {
        if (dto == null) {
            return null;
        }
        Paquete entidad = new Paquete();
        entidad.setNombre(dto.getNombre());
        entidad.setTamaño(aTamañoPaquete(dto.getTamaño()));
        entidad.setProductos(aProductosPaquete(dto.getProductos()));
        entidad.setPrecio(dto.getPrecio());
        entidad.setImagen(dto.getImagen());
        entidad.setIdEmprendedor(dto.getIdEmprendedor());
        return entidad;
    }

    public static TamañoPaquete aTamañoPaquete(TamañoPaqueteDTO dto) {
        if (dto == null) {
            return null;
        }
        return TamañoPaquete.valueOf(dto.name());
    }

    public static ObjectId aObjectId(String id) {
        if (id == null || !ObjectId.isValid(id)) {
            return null;
        }
        return new ObjectId(id);
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
