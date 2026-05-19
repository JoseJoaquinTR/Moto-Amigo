package Adapter;

import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.Entidades.TamañoPaquete;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.productosdto.ProductoDTO;
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
import enums.TamañoPaqueteDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author joset
 */
public class AdapterPaqueteAPaqueteDTO {

    private final AdapterProductoAProductoDTO adapterProducto;

    public AdapterPaqueteAPaqueteDTO() {
        this.adapterProducto = new AdapterProductoAProductoDTO();
    }

    public PaqueteDTO adaptarADTO(Paquete paquete) {
        if (paquete == null) {
            return null;
        }
        PaqueteDTO dto = new PaqueteDTO();
        dto.setIdPaquete(paquete.getId());
        dto.setNombre(paquete.getNombre());
        if (paquete.getTamaño() != null) {
            dto.setTamaño(TamañoPaqueteDTO.valueOf(paquete.getTamaño().name()));
        }
        dto.setPrecio(paquete.getPrecio());
        dto.setImagen(paquete.getImagen());
        dto.setIdEmprendedor(paquete.getIdEmprendedor());
        dto.setProductos(adaptarProductosPaquetesADTO(paquete.getProductos(), paquete.getProductosResueltos()));
        return dto;
    }

    /**
     * se encarga de juntar la cantidad y el peso total con el producto
     * correspondiente. se hace mediante el id
     *
     * @param productos cantidad y peso
     * @param productosResueltos producto
     * @return
     */
    private List<ProductosPaqueteDTO> adaptarProductosPaquetesADTO(List<ProductosPaquete> productos, List<Producto> productosResueltos) {
        List<ProductosPaqueteDTO> resultado = new ArrayList<>();
        if (productos == null) {
            return resultado;
        }

        Map<String, Producto> mapa = new HashMap<>();
        if (productosResueltos != null) {
            for (Producto p : productosResueltos) {
                if (p != null && p.getId() != null) {
                    mapa.put(p.getId(), p);
                }
            }
        }

        for (ProductosPaquete pp : productos) {
            if (pp == null || pp.getIdProducto() == null) {
                continue;
            }
            Producto producto = mapa.get(pp.getIdProducto());
            ProductoDTO productoDTO = adapterProducto.adaptarADTO(producto);
            resultado.add(new ProductosPaqueteDTO(productoDTO, pp.getCantidad(), pp.getPesoTotal()));
        }
        return resultado;
    }

    public List<ProductosPaqueteDTO> crearProductoDTOID(List<ProductosPaquete> prodcutos) {
        List<ProductosPaqueteDTO> resultado = new ArrayList<>();
        if (prodcutos == null) {
            return resultado;
        }
        for (ProductosPaquete pp : prodcutos) {
            ProductoDTO p = new ProductoDTO();
            p.setId(pp.getIdProducto());
            resultado.add(new ProductosPaqueteDTO(p, pp.getCantidad(), pp.getPesoTotal()));
        }
        return resultado;
    }

    public Paquete adaptarAEntidad(NuevoPaqueteDTO dto) {
        if (dto == null) {
            return null;
        }
        Paquete entidad = new Paquete();
        entidad.setNombre(dto.getNombre());
        entidad.setTamaño(aTamañoPaqueteEntidad(dto.getTamaño()));
        entidad.setProductos(aProductosPaqueteEntidad(dto.getProductos()));
        entidad.setPrecio(dto.getPrecio());
        entidad.setImagen(dto.getImagen());
        entidad.setIdEmprendedor(dto.getIdEmprendedor());
        return entidad;
    }
    public Paquete EditarPaqueteAdaptarAEntidad(EditarPaqueteDTO dto) {
        if (dto == null) {
            return null;
        }
        Paquete entidad = new Paquete();
        entidad.setNombre(dto.getNombre());
        entidad.setTamaño(aTamañoPaqueteEntidad(dto.getTamaño()));
        entidad.setProductos(aProductosPaqueteEntidad(dto.getProductos()));
        entidad.setPrecio(dto.getPrecio());
        entidad.setImagen(dto.getImagen());
        return entidad;
    }

    public TamañoPaquete aTamañoPaqueteEntidad(TamañoPaqueteDTO dto) {
        if (dto == null) {
            return null;
        }
        return TamañoPaquete.valueOf(dto.name());
    }

    public List<ProductosPaquete> aProductosPaqueteEntidad(List<ProductosPaqueteDTO> dtos) {
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
