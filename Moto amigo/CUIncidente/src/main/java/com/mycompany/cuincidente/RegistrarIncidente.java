package com.mycompany.cuincidente;

import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;

/**
 * Implementación del caso de uso para registrar un incidente.
 * Delega el registro a la fachada de negocio.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RegistrarIncidente implements IRegistrarIncidente {

    private final IFachadaNegocio fachada;

    private RegistrarIncidente(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static RegistrarIncidente crear() {
        return new RegistrarIncidente(FachadaNegocio.crear());
    }

    @Override
    public void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException {
        fachada.registrarIncidente(incidenteDTO);
    }
}
