package Adapter;

import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.productosdto.ProductoDTO;
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
import enums.TamañoPaqueteDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joset
 */
public class AdapterPaqueteAPaqueteDTO {

    private final AdapterProductoAProductoDTO adapterProducto;

    public AdapterPaqueteAPaqueteDTO() {
        this.adapterProducto = new AdapterProductoAProductoDTO();
    }

    public PaqueteDTO adaptar(Paquete paquete) {
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
        dto.setProductos(adaptarProductos(paquete.getProductos(), paquete.getProductosResueltos()));
        return dto;
    }
    /**
     * se encarga de juntar la cantidad y el peso total con el producto correspondiente. se hace mediante el id
     * @param productos cantidad y peso
     * @param productosResueltos producto
     * @return 
     */
    private List<ProductosPaqueteDTO> adaptarProductos(List<ProductosPaquete> productos,List<Producto> productosResueltos) {
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
            ProductoDTO productoDTO = adapterProducto.adaptar(producto);
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
}
