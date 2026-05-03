package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del caso de uso para buscar ubicaciones por texto.
 * Delega la búsqueda a la fachada de negocio.
 *
 * @author joset
 */
public class BuscarUbicacion implements IBuscarUbicacion {

    private final IFachadaNegocio fachada;

    private BuscarUbicacion(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static BuscarUbicacion crear() {
        return new BuscarUbicacion(FachadaNegocio.crear());
    }

    @Override
    public List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException {
        if (texto == null || texto.trim().length() < 3) {
            return new ArrayList<>();
        }
        return fachada.buscarUbicacion(texto.trim());
    }
}
