
package Adapter;
import com.mycompany.Entidades.Ubicacion;
import com.mycompany.motoamigodto.UbicacionDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author joset
 */

public class AdapterUbicacionAUbicacionDTO {

    public UbicacionDTO adaptar(Ubicacion ubicacion) {
        if (ubicacion == null) return null;
        return new UbicacionDTO(
            ubicacion.getLatitud(),
            ubicacion.getLongitud(),
            ubicacion.getDescripcion()
        );
    }

    public List<UbicacionDTO> adaptarLista(List<Ubicacion> ubicaciones) {
        if (ubicaciones == null) return new ArrayList<>();
        return ubicaciones.stream()
                .map(this::adaptar)
                .collect(Collectors.toList());
    }
}
