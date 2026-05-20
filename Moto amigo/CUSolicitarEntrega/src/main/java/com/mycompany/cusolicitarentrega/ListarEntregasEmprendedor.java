package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;
import java.util.List;

/**
 * Implementación del caso de uso para listar entregas de un emprendedor.
 * Delega la consulta a la fachada de negocio.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ListarEntregasEmprendedor implements IListarEntregasEmprendedor {

    private final IFachadaNegocio fachada;

    private ListarEntregasEmprendedor(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static ListarEntregasEmprendedor crear() {
        return new ListarEntregasEmprendedor(FachadaNegocio.crear());
    }

    @Override
    public List<EntregaDTO> listarEntregasEmprendedor(String idEmprendedor) throws NegocioException {
        return fachada.obtenerEntregasEmprendedor(idEmprendedor);
    }
}
