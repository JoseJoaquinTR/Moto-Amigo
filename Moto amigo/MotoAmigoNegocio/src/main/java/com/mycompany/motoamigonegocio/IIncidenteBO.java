package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.IncidenteDTO;

/**
 * Operaciones de negocio relacionadas con el registro y consulta de incidentes.
 *
 * @author joset
 */
public interface IIncidenteBO {

    /**
     * Registra un nuevo incidente en el sistema.
     *
     * @param incidenteDTO datos del incidente a registrar.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException;
}
