
package Adapter;

import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author joset
 */
public class AdapterRepartidorARepartidorDTO {

    public RepartidorDTO adaptar(Repartidor repartidor) {
        if (repartidor == null) {
            return null;
        }
//        return new RepartidorDTO(
//                repartidor.getIdRepartidor(),
//                repartidor.getNombre(),
//                repartidor.getTelefono(),
//                repartidor.getCorreo(),
//                repartidor.getVehiculo(),
//                repartidor.getEstado()
//        );
return null;
    }

    public List<RepartidorDTO> adaptarLista(List<Repartidor> repartidores) {
        if (repartidores == null) {
            return new ArrayList<>();
        }
        return repartidores.stream().map(this::adaptar).collect(Collectors.toList());
    }
}