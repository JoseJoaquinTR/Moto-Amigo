package Adapter;

import com.mycompany.Entidades.Imagen;
import com.mycompany.emprendedoresdto.ImagenDTO;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class AdapterImagen {

    public AdapterImagen() {
    }

    /**
     * Metodo para adapter la DTO de una imagen a una entidad que se guardara
     *
     * @param imagenDTO la DTO de la imagen que se adaptara
     * @return la entidad de la Imagen
     */
    public static Imagen dtoAImagen(ImagenDTO imagenDTO) {
        if(imagenDTO == null){
            return null;
        }
        Imagen imagen = new Imagen();
        imagen.setIdImagen(new ObjectId().toHexString());
        imagen.setImagen(imagenDTO.getImagen());

        return imagen;
    }

    /**
     * Metodo que adapta una Imagen a una ImagenDTO
     *
     * @param imagen la imagen que se adaptara a DTO
     * @return la ImagenDTO
     */
    public static ImagenDTO imagenADTO(Imagen imagen) {
        return new ImagenDTO(imagen.getImagen());
    }
}
