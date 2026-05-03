package com.mycompany.cuincidente;

import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Caso de uso para registrar un incidente reportado por un repartidor.
 *
 * @author Carmen Andrea Lara Osuna
 */
public interface IRegistrarIncidente {

    /**
     * Registra un nuevo incidente en el sistema.
     *
     * @param incidenteDTO datos del incidente a registrar.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException;
}
