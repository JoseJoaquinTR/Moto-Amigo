package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;

/**
 * Implementación del caso de uso para consultar una ruta.
 * Delega el cálculo a la fachada de negocio.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ConsultarRuta implements IConsultarRuta {

    private final IFachadaNegocio fachada;

    private ConsultarRuta(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static ConsultarRuta crear() {
        return new ConsultarRuta(FachadaNegocio.crear());
    }

    @Override
    public RutaResponseDTO consultarRuta(RutaRequestDTO rutaDTO) throws NegocioException {
        return fachada.calcularRuta(rutaDTO);
    }
}
