package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;
import java.util.List;

/**
 * Implementación del caso de uso para listar entregas de un repartidor.
 * Delega la consulta a la fachada de negocio.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ListarEntregasRepartidor implements IListarEntregasRepartidor {

    private final IFachadaNegocio fachada;

    private ListarEntregasRepartidor(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static ListarEntregasRepartidor crear() {
        return new ListarEntregasRepartidor(FachadaNegocio.crear());
    }

    @Override
    public List<EntregaDTO> listarEntregasRepartidor(Long idRepartidor) throws NegocioException {
        return fachada.obtenerEntregasRepartidor(idRepartidor);
    }
}
