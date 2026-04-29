package Adapter;

import com.mycompany.Entidades.Entrega;
import com.mycompany.motoamigodto.EntregaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterEntregaAEntregaDTO {

    public EntregaDTO adaptar(Entrega entrega) {
        if (entrega == null) return null;
        return new EntregaDTO(
            entrega.getDireccionOrigen(),
            entrega.getDireccionDestino(),
            entrega.getTipoPaquete(),
            entrega.getEstadoEntrega(),
            entrega.getPesoAprox(),
            entrega.getCosto()
        );
    }
    public List<EntregaDTO> adaptarLista(List<Entrega> entregas) {
        if (entregas == null) {
            return new ArrayList<>();
        }
        return entregas.stream().map(this::adaptar).collect(Collectors.toList());
    }
}
