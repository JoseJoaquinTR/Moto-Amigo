package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.IncidenteDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la lógica de negocio para incidentes. Implementación mock
 * que registra el incidente en log mientras no exista persistencia real.
 *
 * @author joset
 */
public class IncidenteBO implements IIncidenteBO {

    private static IncidenteBO instancia;

    private IncidenteBO() {
    }

    /**
     * Obtiene la instancia única del BO.
     *
     * @return instancia de IncidenteBO.
     */
    public static synchronized IncidenteBO getInstancia() {
        if (instancia == null) {
            instancia = new IncidenteBO();
        }
        return instancia;
    }

    @Override
    public void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException {
        if (incidenteDTO == null) {
            throw new NegocioException("El incidente no puede ser nulo.");
        }
        if (incidenteDTO.getTipoIncidente() == null || incidenteDTO.getTipoIncidente().isEmpty()) {
            throw new NegocioException("El tipo de incidente es obligatorio.");
        }
        Logger.getLogger(IncidenteBO.class.getName()).log(
                Level.INFO,
                "Incidente registrado (mock): tipo={0}, descripcion={1}",
                new Object[]{incidenteDTO.getTipoIncidente(), incidenteDTO.getDescripcion()}
        );
    }
}
