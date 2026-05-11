package Adapter;

import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.motoamigodto.PaqueteDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import com.mycompany.motoamigodto.ProductosPaqueteDTO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import enums.TamañoPaqueteDTO;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class AdapterPaqueteAPaqueteDTO {

    private final IFachadaPersistencia fachada;
    private final AdapterProductoAProductoDTO adapterProducto;

    public AdapterPaqueteAPaqueteDTO() {
        this.fachada = FachadaPersistencia.getInstancia();
        this.adapterProducto = new AdapterProductoAProductoDTO();
    }

    public PaqueteDTO adaptar(Paquete paquete) throws PersistenciaException {
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
        dto.setProductos(adaptarProductos(paquete.getProductos()));
        return dto;
    }

    private List<ProductosPaqueteDTO> adaptarProductos(List<ProductosPaquete> productos)
            throws PersistenciaException {
        List<ProductosPaqueteDTO> resultado = new ArrayList<>();
        if (productos == null) {
            return resultado;
        }
        for (ProductosPaquete pp : productos) {
            if (pp == null || pp.getIdProducto() == null) {
                continue;
            }
            Producto producto = fachada.consultarProductoPorId(pp.getIdProducto());
            ProductoDTO productoDTO = adapterProducto.adaptar(producto);
            resultado.add(new ProductosPaqueteDTO(productoDTO, pp.getCantidad(), pp.getPesoTotal()));
        }
        return resultado;
    }

}
