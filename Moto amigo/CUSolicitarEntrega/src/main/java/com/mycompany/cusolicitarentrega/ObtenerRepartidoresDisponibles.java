package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;
import java.util.List;

/**
 * Implementación del caso de uso para obtener repartidores disponibles.
 * Delega la consulta a la fachada de negocio.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ObtenerRepartidoresDisponibles implements IObtenerRepartidoresDisponibles {

    private final IFachadaNegocio fachada;

    private ObtenerRepartidoresDisponibles(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static ObtenerRepartidoresDisponibles crear() {
        return new ObtenerRepartidoresDisponibles(FachadaNegocio.crear());
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidoresDisponibles() throws NegocioException {
        return fachada.obtenerRepartidoresDisponibles();
    }
}
