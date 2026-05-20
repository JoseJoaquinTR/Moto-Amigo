package Adapter;

import com.mycompany.Entidades.Direccion;
import com.mycompany.emprendedoresdto.DireccionDTO;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class AdapterDireccion {

    public AdapterDireccion() {
    }

    /**
     * Convierte una DireccionDTO a una Direccion de persistencia
     *
     * @param direccionDTO la dto que se convertira
     * @return una entidad de direccion
     */
    public static Direccion dtoADireccion(DireccionDTO direccionDTO) {
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(new ObjectId().toHexString());
        direccion.setCalle(direccionDTO.getCalle());
        direccion.setNumero(direccionDTO.getNumero());
        direccion.setColonia(direccionDTO.getColonia());
        direccion.setCodigoPostal(direccionDTO.getCodigoPostal());

        return direccion;
    }

    /**
     * Convierte una entidad de Direccion a una DireccionDTO
     *
     * @param direccion la entidad que se convertira en DTO
     * @return la DTO de la direccion que se mando
     */
    public static DireccionDTO direccionADTO(Direccion direccion) {
        return new DireccionDTO(
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getColonia(),
                direccion.getCodigoPostal());
    }
}
