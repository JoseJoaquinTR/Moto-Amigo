package Adapter;

import Enums.TipoEnvio;
import com.mycompany.Entidades.Entrega;
import com.mycompany.Entidades.Sobre;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.SobreDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.paquetesdto.PaqueteDTO;
import enums.TipoEnvioDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterEntregaAEntregaDTO {

    public EntregaDTO adaptar(Entrega entrega) {
        if (entrega == null) {
            return null;
        }

        EntregaDTO dto = new EntregaDTO();
        dto.setId(entrega.getId());
        dto.setIdEmprendedor(entrega.getIdEmprendedor());
        dto.setIdRepartidor(entrega.getIdRepartidor());
        dto.setDireccionOrigen(entrega.getDireccionOrigen());
        dto.setDireccionDestino(entrega.getDireccionDestino());
        dto.setTipo(entrega.getTipo() != null
                ? TipoEnvioDTO.valueOf(entrega.getTipo().name())
                : null);
        dto.setSobre(adaptarSobre(entrega.getSobre()));
        dto.setEstadoEntrega(entrega.getEstadoEntrega());
        dto.setPesoTotal(entrega.getPesoTotal());
        dto.setDistancia(entrega.getDistancia());
        dto.setCosto(entrega.getCosto());
        dto.setFechaCreacion(entrega.getFechaCreacion());
        dto.setFechaEntrega(entrega.getFechaEntrega());

        dto.setPaquetes(null);

        return dto;
    }

    private SobreDTO adaptarSobre(Sobre sobre) {
        if (sobre == null) {
            return null;
        }
        return new SobreDTO(
                sobre.getLargo(),
                sobre.getAncho(),
                sobre.getAlto(),
                sobre.getPeso(),
                sobre.getDescripcion()
        );
    }

    public List<EntregaDTO> adaptarLista(List<Entrega> entregas) {
        if (entregas == null) {
            return new ArrayList<>();
        }

        return entregas.stream()
                .map(this::adaptar)
                .collect(Collectors.toList());
    }
    
    public Entrega adaptarAEntidad(SolicitudEntregaDTO dto) {
        if (dto == null) {
            return null;
        }

        Entrega entrega = new Entrega();
        entrega.setIdEmprendedor(dto.getIdEmprendedor());
        entrega.setDireccionOrigen(dto.getOrigen());
        entrega.setDireccionDestino(dto.getDestino());
        entrega.setTipo(dto.getTipo() != null
                ? TipoEnvio.valueOf(dto.getTipo().name())
                : null);
        entrega.setEstadoEntrega("DISPONIBLE");
        entrega.setPesoTotal(dto.getPesoTotal());
        entrega.setDistancia(dto.getDistancia());
        entrega.setCosto(dto.getCosto() != null ? dto.getCosto() : 0);
        entrega.setFechaCreacion(LocalDateTime.now());

        if (dto.getTipo() == TipoEnvioDTO.PAQUETES && dto.getPaquetes() != null) {
            List<String> ids = new ArrayList<>();
            for (PaqueteDTO p : dto.getPaquetes()) {
                if (p != null && p.getIdPaquete()!= null) {
                    ids.add(p.getIdPaquete());
                }
            }
            entrega.setIdsPaquetes(ids);
        }

        if (dto.getTipo() == TipoEnvioDTO.SOBRE && dto.getSobre() != null) {
            Sobre sobre = new Sobre();
            sobre.setLargo(dto.getSobre().getLargo());
            sobre.setAncho(dto.getSobre().getAncho());
            sobre.setAlto(dto.getSobre().getAlto());
            sobre.setPeso(dto.getSobre().getPeso());
            sobre.setDescripcion(dto.getSobre().getDescripcion());
            entrega.setSobre(sobre);
        }

        return entrega;
    }

}
